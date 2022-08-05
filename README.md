# Zowe CLI Global Team Config Analyze Tool  
  
This project provides small programs to inspect the way the Zowe CLI Global Team Configuration is used for gathering Zowe CLI configuration and authentication information.  
  
The source code demonstrates a way to read from the OS credential store where Zowe CLI stores its username, password, and team config file path information. The code shows how this can be read and decrypted.  
  
More over, the code demonstrates JSON parsing of the prudent objects from the credential store and team configuration file.  
  
## ConfigKeyTar class

Executing ConfigKeyTar.main() method, the following example output will be produced. The first string is the encrypted string stored in the OS credential store in Windows OS or KeyChain in macOS. The second set of string output is the decryption of the previous string in clear readable Json test format.  

eyJDOlxcVXNlcnNcXGZnODkyMTA1XFx6b3dlLmNvbmZpZy5qc29uIjp7InByb2ZpbGVzLmJhc2UucHJvcGVydGllcy51c2VyIjoiQ0NTQVVUTyIsInByb2ZpbGVzLmJhc2UucHJvcGVydGllcy5wYXNzd29yZCI6IkNDU0FVVE8ifSwiQzpcXFVzZXJzXFxmZzg5MjEwNVxcSWRlYVByb2plY3RzXFxab3dlQ0NTU1ZDU3ltcHRvbXNSZXBvcnRcXHpvd2UuY29uZmlnLmpzb24iOnsicHJvZmlsZXMuYmFzZS5wcm9wZXJ0aWVzLnVzZXIiOiJmZzg5MjEwNSIsInByb2ZpbGVzLmJhc2UucHJvcGVydGllcy5wYXNzd29yZCI6ImphdmFzZGsxIn19
  
{"C:\\Users\\fg892105\\zowe.config.json":{"profiles.base.properties.user":"CCSAUTO","profiles.base.properties.password":"CCSAUTO"},"C:\\Users\\fg892105\\IdeaProjects\\ZoweCCSSVCSymptomsReport\\zowe.config.json":{"profiles.base.properties.user":"fg892105","profiles.base.properties.password":"javasdk1"}}
teamconfig.Config{location='C:\Users\fg892105\zowe.config.json', userName='CCSAUTO', password='CCSAUTO'}
teamconfig.Config{location='C:\Users\fg892105\IdeaProjects\ZoweCCSSVCSymptomsReport\zowe.config.json', userName='fg892105', password='javasdk1'}
  
{"C:\\Users\\fg892105\\zowe.config.json":{"profiles.base.properties.user":"CCSAUTO","profiles.base.properties.password":"fakepw"},"C:\\Users\\fg892105\\IdeaProjects\\ZoweCCSSVCSymptomsReport\\zowe.config.json":{"profiles.base.properties.user":"fg892105","profiles.base.properties.password":"fakepw"}}
teamconfig.Config{location='C:\Users\fg892105\zowe.config.json', userName='CCSAUTO', password='fakepw'}
teamconfig.Config{location='C:\Users\fg892105\IdeaProjects\ZoweCCSSVCSymptomsReport\zowe.config.json', userName='fg892105', password='fakepw'}
  
{"C:\\Users\\fg892105\\zowe.config.json":{"profiles.base.properties.user":"CCSAUTO","profiles.base.properties.password":"fakepw"}}
teamconfig.Config{location='C:\Users\fg892105\zowe.config.json', userName='CCSAUTO', password='fakepw'}  
  
