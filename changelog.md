## 0.3.0: 
### Major affix/gem refactor, existing datapacks will break
- Removed elemental loot categories, instead separating by STAFF or SPELL_WEAPON
- Elemental affixes will now sort automatically depending on which spell power attributes are present on gear
- Changed default elemental affixes to addition instead of multiply (looks nicer in tooltips)
- Made Jewelry and Better Combat optional

## 0.2.1:
- Fix descriptions on Apotheosis
- Fix apotheosis datapack using some invalid attributes

## 0.2.0
- Add Telepathic Affix to all magic weapons (Mob drops will teleport to you)
- Add compatibility with Apotheosis via Sinytra Connector (You'll need to add "apotheosis": "zenith" under globalModAliases in the connector.json config)
- Specify requiring better combat