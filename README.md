![# Down The Plumeria Lane](resources/images/misc/header.png)

## Building

Builds have been stalled since commit [`1a1e06e`](https://github.com/twofacedflora/Down-The-Plumeria-Lane/tree/1a1e06eb31e26b47237644f4975267a015dd7479) due to an issue with nested dependencies. You can access the last functioning build [here](https://github.com/twofacedflora/Down-The-Plumeria-Lane/releases/tag/v0.1.0-alpha). If you'd like to compile the project yourself, try the following steps:

1. Ensure you have the latest Java version installed. You can download the latest release from [Oracle](https://www.oracle.com/java/technologies/downloads/). OpenJDK builds such as [Liberica JDK](https://bell-sw.com/pages/downloads/) should work fine.
2. [Download](https://github.com/twofacedflora/Down-The-Plumeria-Lane/archive/refs/heads/master.zip) the repository.
3. Open the project folder in a terminal and run `./build.sh`.
4. The compiled project should appear in `output`.

## Troubleshooting

**Java Application Launch Failed**

Open the terminal and run `java -jar <PATH_TO_JAR>`. It's known that opening the program using JavaLauncher causes it to crash. The reason for this is currently unknown.

**Cannot Access File**

Ensure that you're entering the correct pathname. In some cases, the run command is case sensitive, so something like `java -jar output/dtpl.jar` won't work.

**Permission Denied (macOS/Linux only)**

Run `chmod +x build.sh` to give yourself access to open the file.

**Program Terminates Upon Closing Terminal**

Not so much a problem, but if you want the program to run even after closing the terminal, prepend `nohup` to the run command. If you don't want `nohup` to generate `nohup.out`, append `>/dev/null 2>&1` to the run command as well.

**Didn't help?**

If you're experiencing a problem that isn't documented in this section, [open an issue](https://github.com/twofacedflora/Down-The-Plumeria-Lane/issues/new) about it.
