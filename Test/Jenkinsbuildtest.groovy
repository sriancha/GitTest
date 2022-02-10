pipeline{
    agent any
        stages {
            stage ("Clone-branch new"){
                steps{
                    println "clone the repositry into local"

                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git-auth', url: 'https://github.com/sriancha/GitToday.git']]])
                    sh "ls -lart ./*"
                }
            }
            stage ("Build"){
               steps{
                   println " Build the job "
                   sh "mvn clean package"
               } 
            }
            stage ("Upload"){
                steps{
                    prnitln " depoly the code into any branch"

                    sh " ls -lart"
                    sh "aws s3 cp target/hello-*.war s3://pipelineart "
                }
            }
        }
    
}