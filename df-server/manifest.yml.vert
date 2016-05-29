---
applications:
- name: dataflow
  memory: 512M
  instances: 1
  host: dataflow
  path: spring-cloud-dataflow-server-cloudfoundry-1.0.0.BUILD-SNAPSHOT.jar
  services:
   - redis
   - rabbit
buildpack: https://github.com/cloudfoundry/java-buildpack
env:
  CLOUDFOUNDRY_API_ENDPOINT: https://api.vert.fe.gopivotal.com
  CLOUDFOUNDRY_ORGANIZATION: TELCO
  CLOUDFOUNDRY_SPACE: cdaver
  CLOUDFOUNDRY_DOMAIN: vert.fe.gopivotal.com
  CLOUDFOUNDRY_SERVICES: redis,rabbit
  CLOUDFOUNDRY_USERNAME: cdaver@pivotal.io
  CLOUDFOUNDRY_PASSWORD: password
  CLOUDFOUNDRY_SKIP_SSL_VALIDATION: true
  CLOUDFOUNDRY_MEMORY: 512
