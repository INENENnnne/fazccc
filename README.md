# ⚔️ FactionPlugin

**Plugin Minecraft complet de gestion de factions — Paper 1.21**

FactionPlugin est un plugin tout-en-un qui transforme votre serveur en véritable terrain de guerres entre factions : **claims**, **alliances**, **guerres**, **banque d'émeraudes**, **shop mondial**, **troc**, **homes**, **coffres privés**, **téléportation**, **statistiques**, **système de puissance** et même une **carte/mini-map des claims** et la **liaison au site web** du serveur !

---

## ✨ Nouveautés de la v5.9.1

- 🛡️ **Guerre entre factions** (`/faction guerre`) : déclaration, acceptation, refus, capitulation et **pillage du coffre du vaincu**.
- 🤝 **Alliances** (`/faction alliance`) : inviter, accepter, refuser et rompre les alliances entre factions.

- 🏠 **Homes de faction** : `/faction sethome`, `/faction home`, `/faction delhome`, avec **2 spawns de faction** et warmup/cooldown.



- 🔒 **Coffres privés** : verrouillez vos coffres (clic gauche avec `Sneak`), seuls vous pouvez les ouvrir; déverrouillageen un clic.


- 🪄 **Tri de coffre intelligent** (`/faction tri`) : réorganisez automatiquement vos coffres/sacs (tri par catégorie, compactage des piles).
- 🗺️ **Mini-map de faction** : une carte interactive affichant les joueurs, claims, spawns et homes(commande `/faction claimmap`).
- 💻 **Liaison site web** (`/lier`) : connectez votre compte Minecraft à votre compte sur le site du serveur (base MySQL.
.
- 📈 **Purge des effets de potion legacy** au démarrage (nettoyage automatique des effets obsolètes sur les joueurs connectés).

Et toutes les fonctionnalités des versions précédentes(shop, stats, puissance, claims, banque, troc, invsee...).

---

## 📦 Installation

1. Téléchargez la dernière version depuis la **[page des releases](../../releases/latest)**.
2. Placez `FactionPlugin-5.9.1.jar` dans le dossier `plugins/` de votre serveur **Paper 1.21**.
3. Redémarrez votre serveur.
4. Le fichier `config.yml` est généré automatiquement dans `plugins/FactionPlugin/`.

> ⚠️ Nécessite **Java 21** et **Paper 1.21** (ou compatible Spigot/Paper dérivé).

---

## 🎮 Fonctionnalités

### 🏰 Factions & Clans
- Création, dissolution, invitation, expulsion et transfert de chef
- **7 rangs de puissance** : Pierre → Bronze → Argent → Or → Diamant → Émeraude → Légendaire
- Effets passifs progressifs(vitesse, force, résistance, régénération...) selon le rang**
- **Tab list** personnalisée avec le nom de la faction et le rang de chaque joueur

### 🗺️ Claims & Territoire
- Réclamez et protégez vos chunks
- **Permissions par joueur** pour construire/casser dans les zones claimées
- **Visualisation des claims** et **carte mini-map** interactive
- **Alliances** pour partager la proximité et la protection

### ⚔️ Guerre & Pillage
- Déclarez la guerre à une faction rivale
- Accepter, refuser, capituler
- **Pillage du coffre partagé** du vaincu après une guerre réussie

### 💰 Économie & Shop
- **Banque d'émeraudes de faction** avec GUI et historique
- **Shop global paginé** : vente, achat, recherche et tri par prix
- Monnaies : fer, or, diamant, émeraude
- **Troc sécurisé** entre joueurs(GUI avec confirmation mutuelle, protection anti-scam)

### 📊 Stats & Puissance
- Statistiques joueurs complètes(kills, mobs, dégâts, blocs, temps de jeu, KDR..
- Classements top joueurs par catégorie
- **Puissance Individuelle** calculée sur PvP, survie, progression et activité
- **Puissance Globale** de la faction + bonus d'alliances

### 🏠 Vie privée & Confort
- **Homes personnels** (`/sethome`, `/home`, `/delhome`, `/homes`)
- **Coffres privés** verrouillables
- **Tri de coffre** automatique
- **Téléportation** : `/tpa`, `/tpaccept`, `/tpdeny`, et TP vers les membres
- **Inventaire partagé** de faction

### 🌐 Intégration Web
- `/lier [statut]` : liez votre compte Minecraft à votre compte du site web
- **Synchronisation cartographique** des claims avec le site (WebMapSync)

---

## ⌨️ Commandes principales

| Commande | Description |
|---|---|
| `/faction` | Menu GUI principal des factions |
| `/faction create <nom>` | Créer une faction |
| `/faction info [nom]` | Infos de la faction |
| `/faction invite <joueur>` | Inviter un joueur |
| `/faction kick <joueur>` | Expulser un membre |
| `/faction claim` | Claimer le chunk courant |
| `/faction unclaim` | Retirer un claim |
| `/faction claims` | Voir ses claims |
| `/faction claimmap` | Carte interactive des claims |
| `/faction alliance ...` | Gérer les alliances |
| `/faction guerre ...` | Gérer les guerres |
| `/faction shop` | Ouvrir le shop global |
| `/faction vendre <prix> <monnaie>` | Mettre un item en vente |
| `/faction acheter <ID>` | Acheter un article |
| `/faction recuperer [ID]` | Récupérer une annonce |
| `/faction stats [joueur]` | Voir ses statistiques |
| `/faction classementjoueurs <cat>` | Classement des joueurs |
| `/faction invsee <joueur>` | Voir l'inventaire d'un joueur(admin) |
| `/faction setspawn [1|2]` | Définir le spawn de faction |
| `/faction home [nom]` | Aller à un home |
| `/faction tri` | Trier un coffre |
| `/sethome`, `/home`, `/delhome`, `/homes` | Homes personnels |
| `/tpa`, `/tpaccept`, `/tpdeny` | Téléportation entre joueurs |
| `/lier [statut]` | Lier le compte au site web |

---

## 🔧 Développement

### Prérequis
- **Java 21**
- **Maven 3.9+**

### Compiler

```bash
mvn clean package
```

Le JAR produit se trouve dans `target/FactionPlugin-5.9.1.jar`.

---

## 📜 Historique des versions

- **v5.9.1** : Mise à jour de compatibilité **Paper 1.21.4**, compilation Java 21, corrections diverses(GUI shop, tri, carte des claims, imports/symboles Bukkit modernes).
- **v5.5.1** : Purge des effets de potion legacy au démarrage; corrections banque/troc/shop/home.

- **v5.2** : Tri de coffre intelligent + liaison compte web(`/lier`) + synchronisation web des claims.
- **v5.1** : Guerre entre factions + GUI principal complet.
- **v5.0** : Alliances, homes de faction, coffres privés, TPA.
- **v4.0.0** : Shop global paginé + InvSee admin.
- **v3.2.4** : Claims, banque d'émeraudes, troc, stats, classements, puissance.
- **v3.1.0** : Fusion avec FactionStats(stats joueurs intégrées, classements).
- **v2.0.0** : Système de puissance, rangs et classement des factions.
- **v1.1.0** : GUI, téléportation et inventaire partagé.
- **v1.0.0** : Version initiale.

---

## 📄 License

Ce projet est sous licence **MIT**.
