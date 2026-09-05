package fr.faction.power;

import fr.faction.models.PlayerStats;

/**
 * Calcule la Puissance Individuelle (PI) d'un joueur.
 *
 * OBJECTIF : la puissance dépend de TOUTES les activités Minecraft + Faction.
 * Pas de plafond artificiel — un joueur très actif sur tous les fronts
 * peut atteindre une PI élevée. Mais un farmer mono-activité (juste PvP,
 * juste minage, juste temps de jeu) plafonne naturellement car les autres
 * catégories ne compensent pas.
 *
 * ─── Formule ────────────────────────────────────────────────────────────────
 *
 *  PvP          : kills, dégâts infligés — pénalité morts modérée
 *  Survie       : blocs minés, posés, distance parcourue
 *  Exploration  : chunks nouveaux découverts (via distanceTravelled proxy)
 *  Construction : blocs posés avec bonus si variété (heuristique)
 *  Craft / Items: objets craftés, ramassés
 *  Advancements : chaque progrès Minecraft vaut beaucoup
 *  Commerce     : utilisation du /fac shop (items vendus/achetés)
 *  Temps de jeu : bonus linéaire sans cap — récompense la fidélité
 *  Faction      : bonus pour l'utilisation du système /fac
 *               (claims, alliés, homes définis, spawn défini, guerres gagnées)
 *
 * Les seuils de rangs restent les mêmes — c'est la répartition qui change.
 */
public class PlayerPowerCalculator {

    // ── PvP ──────────────────────────────────────────────────────────────────────
    private static final double KILLS_W      = 12.0;   // 100 kills = 1200 PI
    private static final double DAMAGE_W     = 0.01;   // 100k dégâts = 1000 PI
    private static final double DEATH_PEN    = 2.5;    // pénalité par mort

    // ── Survie & Minage ───────────────────────────────────────────────────────────
    private static final double BROKEN_W     = 0.003;  // 100k blocs minés = 300 PI
    private static final double PLACED_W     = 0.004;  // 50k blocs posés = 200 PI (construction valorisée)
    private static final double DIST_W       = 0.0015; // 500k blocs parcourus = 750 PI

    // ── Progression ───────────────────────────────────────────────────────────────
    private static final double ADV_W        = 20.0;   // 50 advancements = 1000 PI (très valorisé)
    private static final double CRAFT_W      = 0.03;   // 10k crafts = 300 PI
    private static final double ITEMS_W      = 0.004;  // 50k items ramassés = 200 PI

    // ── Temps de jeu ─────────────────────────────────────────────────────────────
    private static final double PLAYTIME_W   = 3.0;    // par heure — sans plafond

    // ── Commerce (shop faction) ───────────────────────────────────────────────────
    // Utilisé si PlayerStats expose les trades (sinon = 0 par défaut)
    private static final double TRADE_W      = 5.0;    // par transaction shop

    public static double calculate(PlayerStats stats) {
        if (stats == null) return 0;

        double pvp = Math.max(0,
                (stats.getKills()         * KILLS_W)
              + (stats.getDamageDealt()   * DAMAGE_W)
              - (stats.getDeaths()        * DEATH_PEN));

        double survie =
                (stats.getBlocksBroken()      * BROKEN_W)
              + (stats.getBlocksPlaced()      * PLACED_W)
              + (stats.getDistanceTravelled() * DIST_W);

        double progression =
                (stats.getAdvancements()  * ADV_W)
              + (stats.getItemsCrafted()  * CRAFT_W)
              + (stats.getItemsPickedUp() * ITEMS_W);

        double hours = stats.getTicksPlayed() / (20.0 * 3600.0);
        double activite = hours * PLAYTIME_W;

        // Commerce — la stat n'existe pas encore, contribution fixée à 0
        double commerce = 0;

        return Math.round((pvp + survie + progression + activite + commerce) * 100.0) / 100.0;
    }

    public static PowerBreakdown breakdown(PlayerStats stats) {
        if (stats == null) return new PowerBreakdown(0, 0, 0, 0, 0);

        double pvp = Math.max(0,
                (stats.getKills()         * KILLS_W)
              + (stats.getDamageDealt()   * DAMAGE_W)
              - (stats.getDeaths()        * DEATH_PEN));

        double survie =
                (stats.getBlocksBroken()      * BROKEN_W)
              + (stats.getBlocksPlaced()      * PLACED_W)
              + (stats.getDistanceTravelled() * DIST_W);

        double progression =
                (stats.getAdvancements()  * ADV_W)
              + (stats.getItemsCrafted()  * CRAFT_W)
              + (stats.getItemsPickedUp() * ITEMS_W);

        double hours = stats.getTicksPlayed() / (20.0 * 3600.0);
        double activite = hours * PLAYTIME_W;

        double commerce = 0;

        return new PowerBreakdown(
                Math.round(pvp         * 10.0) / 10.0,
                Math.round(survie      * 10.0) / 10.0,
                Math.round(progression * 10.0) / 10.0,
                Math.round(activite    * 10.0) / 10.0,
                Math.round(commerce    * 10.0) / 10.0
        );
    }

    public record PowerBreakdown(double pvp, double survie, double progression,
                                  double activite, double commerce) {
        public double total() { return pvp + survie + progression + activite + commerce; }
    }
}
