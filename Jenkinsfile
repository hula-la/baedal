pipeline {
    agent any

    stages {
        stage('build') {
            steps {
               sh ''' 
                echo 'start bootJar' 
	   cd ./backend/
                chmod +x gradlew
                ./gradlew clean bootJar
                '''
            }
        }
    }
}