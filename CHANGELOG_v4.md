# CHANGELOG — FactionPlugin v4.0.0

## v5.10.0 — Sous-chefs (fusion Paper 1.21)

- Ce changement fusionne le rôle **Sous-chef** avec les correctifs de compatibilité
  **Paper 1.21.x** (API Bukkit modernes, Java 21) de la v5.9.1.
- Le numéro de version est passé à **5.10.0**.

### Sous-chefs
- Nouveau rôle **Sous-chef** : le chef peut promouvoir jusqu'à **2** membres au rang de sous-chef
  via `/faction souschef promouvoir <joueur>` (et les rétrograder avec `retirer`).
- Le chef peut régler la limite de sous-chefs autorisés (`/faction souschef limite <0-2>`, plafond absolu = 2).
- `/faction souschef liste` — voir les sous-chefs actuels.
- Un sous-chef peut désormais :
  - Inviter des joueurs (`/faction invite`)
  - Expulser des membres, **sauf le chef** (`/faction kick`)
  - Proposer, accepter, refuser et rompre des alliances (`/faction alliance ...`)
  - Déclarer, accepter et refuser des guerres (`/faction guerre declarer/accepter/refuser`)
  - Définir les spawns de faction (`/faction setspawn`)
  - Claimer / retirer des claims (`/faction claim`, `/faction unclaim`)
- Restent réservés au **chef uniquement** : `setchef`, `rename`, `disband`, `claimallow`/`claimdeny`,
  `perms`, et la capitulation en guerre (`/faction guerre capituler`).
- GUI (`MainMenuGUI` et `FactionGUI`) mis à jour pour refléter ces nouvelles permissions.
- Persistance des sous-chefs et de la limite dans `factions.yml`.

## Nouveautés v4.0.0

### 🛒 Shop Global (`/faction shop`)
- GUI paginé (5 rangées × 9 = 45 items/page)
- Recherche par mot-clé : clic sur le panneau dans le GUI, puis saisie dans le chat
- Tri par prix croissant (`↑`) ou décroissant (`↓`)
- Monnaies acceptées : Lingot de fer, Lingot d'or, Diamant, Émeraude
- Paiement automatique au vendeur dès la vente (ou livré à la reconnexion si hors-ligne)
- Drop à tes pieds si l'inventaire est plein (acheteur ET vendeur)
- Vue "Mes annonces" depuis le GUI ou `/faction mesannonces`

### Commandes shop
| Commande | Description |
|---|---|
| `/faction shop` | Ouvrir le shop (GUI) |
| `/faction vendre <prix> <monnaie>` | Mettre l'item en main en vente |
| `/faction acheter <ID>` | Acheter directement par ID |
| `/faction recuperer [ID]` | Récupérer une annonce non vendue (sans ID = liste) |
| `/faction mesannonces` | Voir ses annonces (GUI) |

Monnaies : `fer`, `or`, `diamant`, `emeraude`

### 👁️ InvSee (`/faction invsee <joueur>`)
- Permission requise : `faction.admin`
- Affiche l'inventaire complet du joueur (36 slots + hotbar + armure + offhand)
- Lecture seule : aucun item ne peut être pris ou déplacé
- Message d'état dans le chat (joueur ciblé + confirmation lecture seule)

## Fichiers ajoutés
```
src/main/java/fr/faction/shop/
  ├── ShopListing.java     ← Modèle d'annonce
  ├── ShopManager.java     ← Logique métier + persistance (shop.yml)
  ├── ShopGUI.java         ← GUI paginé avec recherche + Listener
  └── InvSeeGUI.java       ← GUI InvSee admin + Listener
```

## Modifications
- `FactionPlugin.java` : intégration des nouveaux managers
- `FactionCommand.java` : +8 nouvelles sous-commandes
- `PlayerListener.java` : hook recherche chat + livraison paiements en attente
- `plugin.yml` : version 4.0.0

## Fichier de données
`plugins/FactionPlugin/shop.yml` — auto-créé au premier `/faction vendre`

---

## Historique
- v3.2.4 : Claim, Banque émeraudes, Troc, Stats, Classements, Puissance
- v4.0.0 : Shop Global paginé + InvSee admin
