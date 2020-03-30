<img src="icon.png" align="right" width="180px"/>

# Blueprint


[>> Downloads <<](https://github.com/FabLabsMC/Blueprint/releases)

*Let's get drafting!*

**This mod is open source and under a permissive license.** As such, it can be included in any modpack on any platform without prior permission. We appreciate hearing about people using our mods, but you do not need to ask to use them. See the [LICENSE file](LICENSE) for more details.

This is a template repository for creating Fab Labs projects with automatic CI publishing to the Github Packages maven. Fab Labs projects are typically experimental API drafts that will eventually be PR'd into [Fabric API](https://github.com/fabricmc/fabric). As such, this project is structured around Fabric API, along with its checkstyles and versioning system.

## Setting Up
Setup is designed to be as easy as possible for creating new projects.
1. Click the "Use this template" button above the file view.
2. Clone the newly-created repo to your computer.
3. In [gradle.properties](gradle.properties), change the `library_name` property to be the name of this library. It will be converted into `fablabs-<library_name>-v<version>` to create your Mod ID.
4. In your IDE, change the names of the `io.github.fablabsmc.fablabs.api.blueprint` package and the `io.github.fablabsmc.fablabs.api.blueprint.v1.Blueprint` class as necessary. Change the `MODID` field in `Blueprint` to your mod ID.
5. Change the name of `mixins.blueprint.json` by replacing `blueprint` with your library name. Change the value of `package` in the mixins JSON as necessary.
6. In fabric.mod.json, change the `description` and `entrypoints` fields as necessary.
7. You're all set up to get working!
