start C:\sonarqube\bin\windows-x86-64\StartSonar.bat
cd "IndividualProjBE"
echo Start Review
gradle sonarqube -Dsonar.projectKey=Individual -Dsonar.host.url=http://localhost:9000 -Dsonar.login=06e18b69a612cdc19b7f64d9deac3c8115ee08b7
exit 0
goto :EOF