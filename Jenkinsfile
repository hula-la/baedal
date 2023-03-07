pipeline {
    agent any

    stages {
        stage('build') {
            steps {
               sh ''' 
                echo 'start bootJar' 
                chmod +x gradlew
                ./gradlew clean bootJar
                '''
            }
        }
    }
}