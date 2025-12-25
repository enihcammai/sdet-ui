if not exist "target\surefire-reports\testng-failed.xml" (
    echo No failed tests found from previous run!
    pause
    exit /b 1
)

mvn test -DtestngXml=target\surefire-reports\testng-failed.xml