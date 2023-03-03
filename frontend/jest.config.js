module.exports = {
  testPathIgnorePatterns: ["<rootDir>/client/node_modules/"],
  transform: {
    "^.+\\.(js|jsx|ts|tsx)$": "ts-jest",
  },
  moduleNameMapper: {
    "^src/(.*)$": "<rootDir>/src/$1",
    "^.+\\.svg$": "jest-svg-transformer",
    "\\.(css|less|scss|sass)$": "identity-obj-proxy",
  },
  testEnvironment: "jsdom",
  testEnvironment: 'jest-environment-jsdom',
};