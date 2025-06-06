# FROM		<Image>
# ADD		<host files> <container files>
# RUN		<command>
# ENV		<Env Var> <Env Val>
# WORKDIR	<dir>
# EXPOSE	<port #>
# ENTRYPOINT	<command to be executed>

FROM bellsoft/liberica-openjdk-alpine:24-cds

# Install curl and jq
RUN apk add curl jq

# create a workspace in the container
WORKDIR /home/selenium-docker

# Add required files from target/docker-resources in the local drive to the above WORKDIR in the container
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh

# Fix for windows
RUN dos2unix runner.sh

# Start the runner.sh
ENTRYPOINT sh runner.sh
