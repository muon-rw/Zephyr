## 0.5.0:
- Fixed startup crash
- Added new optional gems and affixes for More RPG Classes (thanks Fichte!)
- Effectively disabled the Shield Breaker test for Heavy Weapons if Better Combat is loaded (only determined by 1h vs. 2h)
- Remove unnecessary Porting Lib jar-in-jars
- Rewrote elemental affix filtering to be more compatible

## 0.4.2:
- Updated to support Zenith 1.2.1

## 0.4.1:
- Fixed mixin crash on Fabric

## 0.4.0:
### Major Refactor, existing datapacks will break 
#### (Again, sorry! Last one for the foreseeable future.) 
- Updated for the latest Spell Power (0.10.0+)
- Removed `SPELL_WEAPON` loot category, SpellWeapons will now be treated as their default, a `SWORD` or `HEAVY_WEAPON`
- Elemental affixes can now be assigned to any weapon with matching spell power attributes, not just `STAFF`. 
- `STAFF` now appropriately matches only two-handed staffs. Added a `WAND` loot category, similar to `STAFF` but One-handed
- `STAFF`/`WAND` categories are now primarily used for *casting* specific affixes.
- Slightly modified a few default gem bonuses
- Fixed crash with Jewelry and Apotheosis
- Migrated from Projectile Damage to Ranged Weapon API (Zenith will still need to make this change to see full compatibility)

## 0.3.0:
### Major affix/gem refactor, existing datapacks will break
- Removed elemental loot categories, instead separating by STAFF or SPELL_WEAPON
- Elemental affixes will now sort automatically depending on which spell power attributes are present on gear
- Added new gems for Soul and Lightning spell power
- Changed default elemental affixes to addition instead of multiply (looks nicer in tooltips)
- Made Jewelry and Better Combat optional

## 0.2.1:
- Fix descriptions on Apotheosis
- Fix apotheosis datapack using some invalid attributes

## 0.2.0
- Add Telepathic Affix to all magic weapons (Mob drops will teleport to you)
- Add compatibility with Apotheosis via Sinytra Connector (You'll need to add "apotheosis": "zenith" under globalModAliases in the connector.json config)
- Specify requiring better combat