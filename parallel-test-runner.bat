@echo off
echo ========================================
echo Parallel Test Runner
echo ========================================
echo.

REM Опции запуска
set TESTNG_XML=testng.xml
set THREAD_COUNT=2

echo Running tests in parallel with %THREAD_COUNT% threads...
echo Test configuration: %TESTNG_XML%
echo.

REM Запуск тестов через Maven
mvn clean test -DtestngXml=%TESTNG_XML%

if errorlevel 1 (
    echo.
    echo ERROR: Tests failed!
    pause
    exit /b 1
)

echo.
echo SUCCESS: All tests completed!
pause