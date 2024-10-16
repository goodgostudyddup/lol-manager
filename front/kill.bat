@echo off
echo Finding processes listening on port 5500...

:: 查找使用5500端口的进程
for /f "tokens=5" %%a in ('netstat -aon ^| findstr "5500"') do (
    set pid=%%a
    echo Found process with PID: !pid!

    :: 杀死进程
    taskkill /F /PID !pid!
    echo Process with PID !pid! has been killed.
)

echo All processes using port 5500 have been closed.