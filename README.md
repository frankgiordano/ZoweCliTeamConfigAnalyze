# Zowe CLI Global Team Config Analyze Tool Written In Java
  
This project provides small programs to inspect the way the [Zowe CLI](https://github.com/zowe/zowe-cli]) Global Team Configuration is used for gathering Zowe CLI configuration and authentication information.  

The source code demonstrates a way to read from the OS credential store where Zowe CLI stores its username, password, and team config file path information. The code shows how this can be read and decrypted.  
  
Moreover, the code demonstrates JSON parsing of the prudent objects from the credential store and team configuration file.  

The intention of this project is to provide a test bed for developing and implementing Global Team Configuration processing of its profiles and authentication within the [Zowe Java SDK](https://github.com/zowe/zowe-client-java-sdk) project.
  
## KeyTarTest class

Executing KeyTarTest.main() method, the following example output will be produced below. The first string is the encrypted string stored in the OS credential store in Windows OS or KeyChain in macOS. The second set of string output is the decryption of the previous string in clear readable Json text format. The third and fourth string outputs are from mock data being used to represent the returned KeyTar value. If you execute this example on a machine without Zowe CLI and Team Config in place, the output will only be based on the mock data. The KeyTarConfig values are the resultant parsing of the JSON string and storing the values in KeyTarConfig object.   

eyJDOlxcVXNlcnNcXGZnODkyMTA1XFx6b3dlLmNvbmZpZy5qc29uIjp7InByb2ZpbGVzLmJhc2UucHJvcGVydGllcy51c2VyIjoiQ0NTQVVUTyIsInByb2ZpbGVzLmJhc2UucHJvcGVydGllcy5wYXNzd29yZCI6IkNDU0FVVE8ifSwiQzpcXFVzZXJzXFxmZzg5MjEwNVxcSWRlYVByb2plY3RzXFxab3dlQ0NTU1ZDU3ltcHRvbXNSZXBvcnRcXHpvd2UuY29uZmlnLmpzb24iOnsicHJvZmlsZXMuYmFzZS5wcm9wZXJ0aWVzLnVzZXIiOiJmZzg5MjEwNSIsInByb2ZpbGVzLmJhc2UucHJvcGVydGllcy5wYXNzd29yZCI6ImphdmFzZGsxIn19

{"C:\\Users\\fg892105\\zowe.config.json":{"profiles.base.properties.user":"CCSAUTO","profiles.base.properties.password":"CCSAUTO"},"C:\\Users\\fg892105\\IdeaProjects\\ZoweCCSSVCSymptomsReport\\zowe.config.json":{"profiles.base.properties.user":"fg892105","profiles.base.properties.password":"javasdk1"}}
KeyTarConfig{location='C:\Users\fg892105\zowe.config.json', userName='CCSAUTO', password='CCSAUTO'}
KeyTarConfig{location='C:\Users\fg892105\IdeaProjects\ZoweCCSSVCSymptomsReport\zowe.config.json', userName='fg892105', password='javasdk1'}

{"C:\\Users\\fg892105\\zowe.config.json":{"profiles.base.properties.user":"CCSAUTO","profiles.base.properties.password":"fakepw"},"C:\\Users\\fg892105\\IdeaProjects\\ZoweCCSSVCSymptomsReport\\zowe.config.json":{"profiles.base.properties.user":"fg892105","profiles.base.properties.password":"fakepw"}}
KeyTarConfig{location='C:\Users\fg892105\zowe.config.json', userName='CCSAUTO', password='fakepw'}
KeyTarConfig{location='C:\Users\fg892105\IdeaProjects\ZoweCCSSVCSymptomsReport\zowe.config.json', userName='fg892105', password='fakepw'}

{"C:\\Users\\fg892105\\zowe.config.json":{"profiles.base.properties.user":"CCSAUTO","profiles.base.properties.password":"fakepw"}}
KeyTarConfig{location='C:\Users\fg892105\zowe.config.json', userName='CCSAUTO', password='fakepw'}
  
## TeamConfig class  
  
Executing TeamConfigTest.main() method outputs information on parsing the zowe.config.json file. The information about the file and its location is provided by the KeyTarTest class and its processing of the KeyTar stored by the Team Config process on initial setup of Zowe CLI and its usage for username and password encryption store. The parsing of the config file is incomplete at the moment.  
  
## keytar package  
  
The keytar package included in this project provides a clean and fully implemented way to process Zowe CLI Team Config KeyTar credentials store retrieval. Execute the KeyTarContainerTst.main() method for demonstration. For it to work it requires to be executed on a machine with the KeyTar store in place.  
    
## Requirements  
  
  Java 11  
  Maven  
   
  
