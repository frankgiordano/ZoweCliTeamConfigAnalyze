# Zowe Global Team Config Analyze Tool Written In Java
  
This project parses the [Zowe CLI](https://github.com/zowe/zowe-cli]) Global Team Configuration and retrieves authentication information.  

The source code demonstrates a way to read from the OS credential store where Zowe CLI stores its username, password, and team config file path information. The code shows how this can be read and decrypted.  
  
Mainly the code demonstrates JSON parsing of the prudent objects from the credential store and team configuration file and provides APIs to retrieve profile and authentication information from the configuration.  

The intention of this project is to provide a test bed for developing and implementing Zowe Global Team Configuration processing of its profiles and authentication within the [Zowe Java SDK](https://github.com/zowe/zowe-client-java-sdk) project.
  
## ConfigTest class

Executing ConfigTest.main() method, the following example output will be produced below. The first string is the encrypted string stored in the OS credential store in Windows OS or KeyChain in macOS. The second set of string output is the decryption of the previous string in clear readable JSON text format. The third string represent a KeyTarConfig object after parsing JSON string. The fourth string represents Zowe Global Team Configuration JSON string read from the  zowe.config.json file, and the last string represents a ZoweTeamConfig object after JSON string passing.    

eyIvVXNlcnMvZnJhbmNlc2NvZ2lvcmRhbm8vem93ZS5jb25maWcuanNvbiI6eyJwcm9maWxlcy5iYXNlLnByb3BlcnRpZXMudXNlciI6ImZnaW9yZCIsInByb2ZpbGVzLmJhc2UucHJvcGVydGllcy5wYXNzd29yZCI6Ik5FVzRAREFZIn19  
  
{"/Users/francescogiordano/zowe.config.json":{"profiles.base.properties.user":"xxxx","profiles.base.properties.password":"xxxx"}}  
  
KeyTarConfig{location='/Users/francescogiordano/zowe.config.json', userName='xxxx', password='xxxx'}  
  
{"$schema":".\/zowe.schema.json","defaults":{"tso":"tso","ssh":"ssh","zosmf":"zosmf","base":"base"},"profiles":{"tso":{"type":"tso","secure":[],"properties":{"codePage":"1047","logonProcedure":"IZUFPROC","account":""}},"ssh":{"type":"ssh","secure":[],"properties":{"port":22}},"zosmf":{"type":"zosmf","secure":[],"properties":{"port":443}},"base":{"type":"base","secure":["user","password"],"properties":{"rejectUnauthorized":true,"host":"47.19.64.77"}}},"autoStore":true}  
  
ZoweTeamConfig{partitions=[], schema=Schema{schema='./zowe.schema.json'}, profiles=[Profile{name='tso', jsonPropsObj={"codePage":"1047","logonProcedure":"IZUFPROC","account":""}, secure=[], properties=null}, Profile{name='ssh', jsonPropsObj={"port":22}, secure=[], properties=null}, Profile{name='zosmf', jsonPropsObj={"port":443}, secure=[], properties=null}, Profile{name='base', jsonPropsObj={"rejectUnauthorized":true,"host":"47.19.64.77"}, secure=["user","password"], properties=null}], defaults=null, autoStore=null}  
  
## KeyTarTest class  
  
Executing KeyTarTest.main() method outputs information on parsing the key store.  

## TeamConfigTest class  

Executing TeamConfigTest.main() method outputs similair information as ConfigTest.main() but with calling an API method to return a profile object requested with all the needed information.  
  
## Requirements  
  
  Java 11  
  Maven  
   
  
