FROM node:slim
RUN npm install -g serve create-react-app
EXPOSE 5000
ENV NODE_ENV production
WORKDIR /webapp
ADD package.json .
RUN yarn install --network-timeout=100000
ADD . .
RUN if [ -d "build/" ]; then rm -rf build; fi
RUN yarn run build
CMD serve -s build
