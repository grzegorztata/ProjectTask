@rem Uruchomienie runcrud (ok/error)
@rem Otwarcie przegladarki i wpisanie adresu (OK/error)
@rem koniec (erory + end)

call runcrud
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo File runcrud has errors
goto fail

:browser
start vivaldi "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Failed to open browser or address

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished