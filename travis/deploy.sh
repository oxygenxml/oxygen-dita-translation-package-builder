git config user.name "DalinaBivolan";
git config user.email "dalinabivolan@gmail.com";
git fetch;
git checkout master;
git reset;
cp -f target/addon.xml build;
git add build/addon.xml;
git commit -m "New release - ${TRAVIS_TAG}";
git push origin HEAD:master; 