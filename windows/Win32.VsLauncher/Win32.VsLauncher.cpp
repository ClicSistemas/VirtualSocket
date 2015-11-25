// Win32.VsLauncher
// Launcher for VirtualSocket 

#include "stdafx.h"
#include "Win32.VsLauncher.h"
#include <string>
#include <windows.h>

#define MAX_LOADSTRING 100

int APIENTRY wWinMain(_In_ HINSTANCE hInstance,
					 _In_opt_ HINSTANCE hPrevInstance,
					 _In_ LPWSTR    lpCmdLine,
					 _In_ int       nCmdShow)
{
	UNREFERENCED_PARAMETER(hPrevInstance);
	UNREFERENCED_PARAMETER(lpCmdLine);

	// Launchs the VirtualSocket

	// Searchs for the Java	
	std::string curPath = std::string(getenv("PATH"));
	std::string finalPath;
	int retval = 0;
	std::string delimiter = ";";

	size_t pos = 0;
	std::string dir;

	while( (pos = curPath.find(delimiter)) != std::string::npos) {
		dir = curPath.substr(0, pos);

		finalPath = std::string(dir) + "\\javaw.exe";
		retval = PathFileExists(finalPath.c_str());

		curPath.erase(0, pos + delimiter.length());

		if (retval == 1) {
			break;
		}
	}

	// Starts the Aplication
	if (retval == 1) {		
		TCHAR curDir[MAX_PATH];
		DWORD ret = GetCurrentDirectory(MAX_PATH, curDir);

		if (ret != 0) {		
			std::string appPath = finalPath; 
			std::string jarPath = std::string(curDir) + "\\VirtualSocket.jar";
			std::string params = " -jar \"" + jarPath + "\"";

			retval = PathFileExists(jarPath.c_str());

			if (retval == 1) {				
				STARTUPINFO         siStartupInfo;
				PROCESS_INFORMATION piProcessInfo;

				memset(&siStartupInfo, 0, sizeof(siStartupInfo));
				memset(&piProcessInfo, 0, sizeof(piProcessInfo));

				siStartupInfo.cb = sizeof(siStartupInfo);

				LPSTR cmdLine = const_cast<LPSTR>(params.c_str());
				
				if (CreateProcess(appPath.c_str(),      
					cmdLine,
					NULL,
					NULL,
					FALSE,
					CREATE_DEFAULT_ERROR_MODE,
					NULL,
					NULL,
					&siStartupInfo,
					&piProcessInfo))
				{
					return 0;
				}
				else {
					return (int)-1;
				}
			}
			else {
				return (int)-1;
			}
		}
		else {
			return (int)-1;
		}
	}
	else {
		return (int)-1;
	}
}


