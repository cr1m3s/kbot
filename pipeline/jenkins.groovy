pipeline {
    agent any
    parameters {
        choice(name: 'OS', choices: ['linux', 'apple', 'windows'], description: 'Pick OS')
        choice(name: 'ARCH', choices: ['amd64', 'arm64'], description: 'Pick ARCH')
    }

    environment {
        GITHUB_TOKEN=credentials('junk')
        REPO = 'https://github.com/cr1m3s/kbot.git'
        BRANCH = 'main'
    }

    stages {
        
				stage('Example') {
            steps {
                echo "Build for platform ${params.OS}"
                echo "Build for arch: ${params.ARCH}"
            }
        }

        stage('clone') {
            steps {
                echo 'Clone Repository'
                git branch: "${BRANCH}", url: "${REPO}"
						}
        }

        stage('test') {
            steps {
								echo 'Testing started'
								sh "make test"
            }
        }

        stage('build') {
            steps {
                echo "Building binary for platform ${params.OS} on ${params.ARCH} started"
                sh "make build GOOS=${params.OS} GOARCH=${params.ARCH}"
            }
        }

        stage('image') {
            steps {
                echo "Building image for platform ${params.OS} on ${params.ARCH} started"
                sh "make image"
            }
        }

        stage('push image') {
            steps {
                sh "make push"
            }
        }
				
				stage('logout') {
						steps {
								script {
										try {
												node {
														sh "docker logout"
												}
										} catch (Exception e) {
												echo "Error occurred during docker logout: ${e.message}"
												currentBuild.result = 'FAILURE'
												error 'Failed to logout from Docker'
										}
								}
						}
				}
		}
	}
