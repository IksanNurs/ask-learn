"use strict";
const { Model } = require("sequelize");

module.exports = (sequelize, DataTypes) => {
  class Tutor extends Model {
    static associate(models) {
      Tutor.belongsTo(models.Class, {
        foreignKey: "class_id",
        as: "class",
        onUpdate: "CASCADE",
        onDelete: "CASCADE",
      });
    }
  }

  Tutor.init(
    {
      name: {
        type: DataTypes.STRING,
        allowNull: true,
      },
      class_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      date: {
        type: DataTypes.DATE,
        allowNull: true,
        defaultValue: sequelize.literal('CURRENT_TIMESTAMP'),
      }
    },
    {
      sequelize,
      modelName: "Tutor",
      tableName: "Tutors",
      timestamps: false,
    }
  );

  return Tutor;
}; 