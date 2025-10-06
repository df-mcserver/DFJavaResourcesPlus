## simple and unreliable bash script to download the required libraries

set -xeo pipefail

mkdir ./libs
git clone --revision=bc457e697c1fc4e6a8f7f9e5fd45f36d84ec30a5 --depth=1 https://github.com/unnamed/creative/
cd creative
./gradlew build
cp serializer-minecraft/build/libs/creative-serializer-minecraft-1.8.4-SNAPSHOT.jar ../libs/
cp api/build/libs/creative-api-1.8.4-SNAPSHOT.jar ../libs/
cd ..