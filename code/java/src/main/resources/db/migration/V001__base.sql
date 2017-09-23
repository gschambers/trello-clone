CREATE TABLE boards (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY(`id`)
);

CREATE TABLE users (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `firstName` VARCHAR(255) NOT NULL,
  `lastName` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `salt` VARCHAR(8) NOT NULL,
  PRIMARY KEY(`id`),
  UNIQUE(`email`)
);

CREATE TABLE boards_users (
  `boardId` INT NOT NULL,
  `userId` INT NOT NULL,
  UNIQUE(`boardId`, `userId`),
  FOREIGN KEY(`boardId`)
    REFERENCES `boards`(`id`)
    ON DELETE CASCADE,
  FOREIGN KEY(`userId`)
    REFERENCES `users`(`id`)
    ON DELETE CASCADE
);

CREATE TABLE lists (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `boardId` INT NOT NULL,
  `order` SMALLINT NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`boardId`)
    REFERENCES `boards`(`id`)
    ON DELETE CASCADE
);

CREATE TABLE cards (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `boardId` INT NOT NULL,
  `listId` INT NOT NULL,
  `order` SMALLINT NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`boardId`)
    REFERENCES `boards`(`id`)
    ON DELETE CASCADE,
  FOREIGN KEY(`listId`)
    REFERENCES `lists`(`id`)
    ON DELETE CASCADE
);

CREATE TABLE cards_users (
  `cardId` INT NOT NULL,
  `userId` INT NOT NULL,
  UNIQUE(`cardId`, `userId`),
  FOREIGN KEY(`cardId`)
    REFERENCES `cards`(`id`)
    ON DELETE CASCADE,
  FOREIGN KEY(`userId`)
    REFERENCES `users`(`id`)
    ON DELETE CASCADE
);

CREATE TABLE colors (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hexCode` VARCHAR(6) NOT NULL,
  PRIMARY KEY(`id`)
);

CREATE TABLE tags (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `colorId` INT NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`colorId`)
    REFERENCES `colors`(`id`)
    ON DELETE CASCADE
);

CREATE TABLE cards_tags (
  `cardId` INT NOT NULL,
  `tagId` INT NOT NULL,
  UNIQUE(`cardId`, `tagId`),
  FOREIGN KEY(`cardId`)
    REFERENCES `cards`(`id`)
    ON DELETE CASCADE,
  FOREIGN KEY(`tagId`)
    REFERENCES `tags`(`id`)
    ON DELETE CASCADE
);
