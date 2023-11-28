#!/bin/bash

PROJ_ID=taterlib
PROJ_NAME=TaterLib
VERSION=1.1.0-R0.12-SNAPSHOT
GROUP_ID=dev/neuralnexus

# --------------------------- Functions --------------------------------

function prepareFiles() {
  echo "Preparing files for $1"

  cp ../$PROJ_NAME-$1-$VERSION.jar ./
  mv ./$PROJ_NAME-$1-$VERSION.jar ./$PROJ_NAME-$1-$VERSION.zip
  unzip -q ./$PROJ_NAME-$1-$VERSION.zip -d ./$1
  rm -rf ./$PROJ_NAME-$1-$VERSION.zip
}

function build {
  BUKKIT=$1
  BUNGEE=$2
  FABRIC=$3
  FORGE=$4
  NEOFORGE=$5
  SPONGE=$6
  VELOCITY=$7
  OUT_FILE=$8

  echo "Building using Bukkit $BUKKIT, Bungee $BUNGEE, Fabric $FABRIC, Forge $FORGE, NeoForge $NEOFORGE, Sponge $SPONGE, and Velocity $VELOCITY"

  mkdir -p ./$OUT_FILE/$GROUP_ID/$PROJ_ID
  mkdir -p ./$OUT_FILE/META-INF

  # Copy common files
  cp -r ./common/$GROUP_ID/$PROJ_ID/common ./$OUT_FILE/$GROUP_ID/$PROJ_ID
  cp -r ./common/$GROUP_ID/$PROJ_ID/lib ./$OUT_FILE/$GROUP_ID/$PROJ_ID
  cp ../../LICENSE ./$OUT_FILE
  cp ../../LICENSE-API ./$OUT_FILE
  cp ../../README.md ./$OUT_FILE

  # Copy bukkit files
  if [ "$BUKKIT" != "N/A" ]; then
    cp -r ./bukkit-$BUKKIT/$GROUP_ID/$PROJ_ID/bukkit ./$OUT_FILE/$GROUP_ID/$PROJ_ID
    cp ./bukkit-$BUKKIT/plugin.yml ./$OUT_FILE
  fi

  # Copy bungee files
  if [ "$BUNGEE" != "N/A" ]; then
    cp -r ./bungee-$BUNGEE/$GROUP_ID/$PROJ_ID/bungee ./$OUT_FILE/$GROUP_ID/$PROJ_ID
    cp ./bungee-$BUNGEE/bungee.yml ./$OUT_FILE
  fi

  # Copy fabric files
  if [ "$FABRIC" != "N/A" ]; then
    cp -r ./fabric-$FABRIC/$GROUP_ID/$PROJ_ID/fabric ./$OUT_FILE/$GROUP_ID/$PROJ_ID
    cp ./fabric-$FABRIC/fabric.mod.json ./$OUT_FILE
    cp ./fabric-$FABRIC/$PROJ_ID.mixins.json ./$OUT_FILE
    cp -r ./fabric-$FABRIC/assets ./$OUT_FILE
    cp ./fabric-$FABRIC/fabric-$FABRIC-refmap.json ./$OUT_FILE
    cp -r ./fabric-$FABRIC/META-INF/jars ./$OUT_FILE/META-INF
  fi

  # Copy forge files
  if [ "$FORGE" != "N/A" ]; then
    cp -r ./forge-$FORGE/$GROUP_ID/$PROJ_ID/forge ./$OUT_FILE/$GROUP_ID/$PROJ_ID
    cp ./forge-$FORGE/pack.mcmeta ./$OUT_FILE
    cp -r ./forge-$FORGE/$PROJ_NAME.png ./$OUT_FILE
    cp ./forge-$FORGE/META-INF/mods.toml ./$OUT_FILE/META-INF 2>/dev/null || :
    cp ./forge-$FORGE/mcmod.info ./$OUT_FILE 2>/dev/null || :
  fi

  # Copy neoforge files
    if [ "$NEOFORGE" != "N/A" ]; then
      cp -r ./neoforge-$NEOFORGE/$GROUP_ID/$PROJ_ID/neoforge ./$OUT_FILE/$GROUP_ID/$PROJ_ID
      cp ./neoforge-$NEOFORGE/pack.mcmeta ./$OUT_FILE
      cp -r ./neoforge-$NEOFORGE/$PROJ_NAME.png ./$OUT_FILE
      cp ./neoforge-$NEOFORGE/META-INF/mods.toml ./$OUT_FILE/META-INF
    fi

  # Copy sponge files
  if [ "$SPONGE" != "N/A" ]; then
    cp -r ./sponge$SPONGE/$GROUP_ID/$PROJ_ID/sponge ./$OUT_FILE/$GROUP_ID/$PROJ_ID
    cp ./sponge$SPONGE/META-INF/sponge_plugins.json ./$OUT_FILE/META-INF
  fi

  # Copy velocity files
  if [ "$VELOCITY" != "N/A" ]; then
    cp -r ./velocity$VELOCITY/$GROUP_ID/$PROJ_ID/velocity ./$OUT_FILE/$GROUP_ID/$PROJ_ID
    cp ./velocity$VELOCITY/velocity-plugin.json ./$OUT_FILE
  fi

  # Zip Jar contents
  cd ./$OUT_FILE
  zip -qr ../$OUT_FILE.zip ./*
  cd ../

  # Rename Jar
  mv ./$OUT_FILE.zip ./$OUT_FILE.jar

  # Generate hashes
  md5sum ./$OUT_FILE.jar | cut -d ' ' -f 1 > ./$OUT_FILE.jar.md5
  mv ./$OUT_FILE.jar.md5 ../$OUT_FILE.jar.md5
  sha1sum ./$OUT_FILE.jar | cut -d ' ' -f 1 > ./$OUT_FILE.jar.sha1
  mv ./$OUT_FILE.jar.sha1 ../$OUT_FILE.jar.sha1
  sha256sum ./$OUT_FILE.jar | cut -d ' ' -f 1 > ./$OUT_FILE.jar.sha256
  mv ./$OUT_FILE.jar.sha256 ../$OUT_FILE.jar.sha256
  sha512sum ./$OUT_FILE.jar | cut -d ' ' -f 1 > ./$OUT_FILE.jar.sha512
  mv ./$OUT_FILE.jar.sha512 ../$OUT_FILE.jar.sha512

  # Move Jar
  mv ./$OUT_FILE.jar ../$OUT_FILE.jar
}

# --------------------------- Setup --------------------------------

# Make directories
mkdir -p ./target/temp_build
cd ./target/temp_build

# --------------------------- Prepare Common --------------------------------

prepareFiles common

# --------------------------- Prepare Bukkit --------------------------------

BUKKIT_VERSIONS=(b1.7.3 1.2.5 1.6.4 1.7.10 1.13.2 1.15.2 1.20.2)
for BUKKIT_VERSION in "${BUKKIT_VERSIONS[@]}"
do
    prepareFiles bukkit-$BUKKIT_VERSION
done

# --------------------------- Prepare Bungee --------------------------------

BUNGEE_VERSIONS=(1.20)
for BUNGEE_VERSION in "${BUNGEE_VERSIONS[@]}"
do
    prepareFiles bungee-$BUNGEE_VERSION
done

# --------------------------- Prepare Fabric --------------------------------

FABRIC_VERSIONS=(1.14 1.15 1.16 1.17 1.18 1.19 1.20 1.20.2)
for FABRIC_VERSION in "${FABRIC_VERSIONS[@]}"
do
    prepareFiles fabric-$FABRIC_VERSION
done

# --------------------------- Prepare Forge --------------------------------

FORGE_VERSIONS=(1.12.2 1.13.2 1.14.4 1.15.1 1.16.3 1.17.1 1.18 1.19 1.20 1.20.2)
for FORGE_VERSION in "${FORGE_VERSIONS[@]}"
do
    prepareFiles forge-$FORGE_VERSION
done

# --------------------------- Prepare NeoForge --------------------------------

NEOFORGE_VERSIONS=(1.20.2)
for NEOFORGE_VERSION in "${NEOFORGE_VERSIONS[@]}"
do
    prepareFiles neoforge-$NEOFORGE_VERSION
done

# --------------------------- Prepare Sponge --------------------------------

SPONGE_VERSIONS=(6 7 8 9 10 11)
for SPONGE_VERSION in "${SPONGE_VERSIONS[@]}"
do
    prepareFiles sponge$SPONGE_VERSION
done

# --------------------------- Prepare Velocity --------------------------------

VELOCITY_VERSIONS=(3)
for VELOCITY_VERSION in "${VELOCITY_VERSIONS[@]}"
do
    prepareFiles velocity$VELOCITY_VERSION
done

# --------------------------- Build b1.7.3 --------------------------------
MC_VERSION=b1.7.3
BUKKIT_VERSION=b1.7.3
BUNGEE_VERSION=N/A
FABRIC_VERSION=N/A
FORGE_VERSION=N/A
NEOFORGE_VERSION=N/A
SPONGE_VERSION=N/A
VELOCITY_VERSION=N/A
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.2.5 --------------------------------
MC_VERSION=1.2.5
BUKKIT_VERSION=1.2.5
BUNGEE_VERSION=N/A
FABRIC_VERSION=N/A
FORGE_VERSION=N/A
NEOFORGE_VERSION=N/A
SPONGE_VERSION=N/A
VELOCITY_VERSION=N/A
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.6.4 --------------------------------
MC_VERSION=1.6.4
BUKKIT_VERSION=1.6.4
BUNGEE_VERSION=N/A
FABRIC_VERSION=N/A
FORGE_VERSION=N/A
NEOFORGE_VERSION=N/A
SPONGE_VERSION=N/A
VELOCITY_VERSION=N/A
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.7.10 --------------------------------
MC_VERSION=1.7.10
BUKKIT_VERSION=1.7.10
BUNGEE_VERSION=N/A
FABRIC_VERSION=N/A
FORGE_VERSION=N/A
NEOFORGE_VERSION=N/A
SPONGE_VERSION=N/A
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.12 --------------------------------
MC_VERSION=1.12
BUKKIT_VERSION=1.7.10
BUNGEE_VERSION=1.20
FABRIC_VERSION=N/A
FORGE_VERSION=1.12.2
NEOFORGE_VERSION=N/A
SPONGE_VERSION=7
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.13 --------------------------------
MC_VERSION=1.13
BUKKIT_VERSION=1.13.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=N/A
FORGE_VERSION=1.13.2
NEOFORGE_VERSION=N/A
SPONGE_VERSION=N/A
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.14 --------------------------------
MC_VERSION=1.14
BUKKIT_VERSION=1.13.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=1.14
FORGE_VERSION=1.14.4
NEOFORGE_VERSION=N/A
SPONGE_VERSION=N/A
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.15 --------------------------------
MC_VERSION=1.15
BUKKIT_VERSION=1.15.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=1.15
FORGE_VERSION=1.15.1
NEOFORGE_VERSION=N/A
SPONGE_VERSION=8
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.16 --------------------------------
MC_VERSION=1.16
BUKKIT_VERSION=1.15.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=1.16
FORGE_VERSION=1.16.3
NEOFORGE_VERSION=N/A
SPONGE_VERSION=8
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.17 --------------------------------
MC_VERSION=1.17
BUKKIT_VERSION=1.15.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=1.17
FORGE_VERSION=1.17.1
NEOFORGE_VERSION=N/A
SPONGE_VERSION=9
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.18 --------------------------------
MC_VERSION=1.18
BUKKIT_VERSION=1.15.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=1.18
FORGE_VERSION=1.18
NEOFORGE_VERSION=N/A
SPONGE_VERSION=9
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.19 --------------------------------
MC_VERSION=1.19
BUKKIT_VERSION=1.15.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=1.19
FORGE_VERSION=1.19
NEOFORGE_VERSION=N/A
SPONGE_VERSION=10
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.20 --------------------------------
MC_VERSION=1.20
BUKKIT_VERSION=1.20.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=1.20
FORGE_VERSION=1.20
NEOFORGE_VERSION=N/A
SPONGE_VERSION=11
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build 1.20.2 --------------------------------
MC_VERSION=1.20.2
BUKKIT_VERSION=1.20.2
BUNGEE_VERSION=1.20
FABRIC_VERSION=1.20.2
FORGE_VERSION=1.20.2
NEOFORGE_VERSION=1.20.2
SPONGE_VERSION=11
VELOCITY_VERSION=3
OUT_FILE=$PROJ_NAME-$MC_VERSION-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Build Main Artifact --------------------------------
MC_VERSION=N/A
BUKKIT_VERSION=N/A
BUNGEE_VERSION=N/A
FABRIC_VERSION=N/A
FORGE_VERSION=N/A
NEOFORGE_VERSION=N/A
SPONGE_VERSION=N/A
VELOCITY_VERSION=N/A
OUT_FILE=$PROJ_NAME-$VERSION
build $BUKKIT_VERSION $BUNGEE_VERSION $FABRIC_VERSION $FORGE_VERSION $NEOFORGE_VERSION $SPONGE_VERSION $VELOCITY_VERSION $OUT_FILE

# --------------------------- Cleanup --------------------------------
cd ../
rm -rf temp_build
