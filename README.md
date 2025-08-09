# Warframe API - Static JSON Datasets
Fetches static datasets for the following files
- `ExportCustoms_en.json`
- `ExportDrones_en.json`
- `ExportFlavour_en.json`
- `ExportFusionBundles_en.json`
- `ExportGear_en.json`
- `ExportKeys_en.json`
- `ExportRecipes_en.json`
- `ExportRegions_en.json`
- `ExportRelicArcane_en.json`
- `ExportResources_en.json`
- `ExportSentinels_en.json`
- `ExportSortieRewards_en.json`
- `ExportUpgrades_en.json`
- `ExportWarframes_en.json`
- `ExportWeapons_en.json`
- `ExportManifest.json`

For additional information on the file contents, check the [wiki page](https://warframe.fandom.com/wiki/Public_Export#Available_Content).

## Output
When you run the file, it exports to `<user.home>/.madimadica/warframe/exports`.
This can be easily accessed with `Windows + R` to `%userprofile%/.madimadica/warframe/exports` on Windows.

If you wish to change this behavior, edit `Path dirPath` in `Main.java`

### Langauge Codes / i18n
You can add one of the language code exported, edit `api.getIndexFiles()` in `Main.java` to include a single String parameter.
