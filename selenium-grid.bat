@echo off
setlocal enabledelayedexpansion

set HUB_PORT=4444
set NODE_PORT=5555

REM Проверка наличия Selenium Server
if not exist "selenium-server.jar" (
    echo Downloading Selenium Server...
    curl -L -o selenium-server.jar https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.16.0/selenium-server-4.16.0.jar
    if errorlevel 1 (
        echo ERROR: Failed to download Selenium Server
        pause
        exit /b 1
    )
)

echo.
echo 1. Starting Selenium Grid Hub...
start "Selenium Hub" cmd /k "java -jar selenium-server.jar hub --port %HUB_PORT%"

echo Waiting for Hub to start...
timeout /t 5 /nobreak >nul

echo.
echo 2. Starting Selenium Grid Node...
start "Selenium Node" cmd /k "java -jar selenium-server.jar node --port %NODE_PORT% --hub http://localhost:%HUB_PORT%/"

echo.
echo ========================================
echo Selenium Grid is starting...
echo Hub: http://localhost:%HUB_PORT%
echo Node: http://localhost:%NODE_PORT%
echo ========================================
echo.
echo Press Ctrl+C in each window to stop the services
echo.
pause