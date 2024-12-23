"use strict";
const { Model } = require("sequelize");

module.exports = (sequelize, DataTypes) => {
  class User extends Model {
    static associate(models) {
      // Menambahkan relasi jika diperlukan
      // Misalnya User hasOne Lab, atau hubungan lainnya.
    }
  }

  User.init(
    {
      username: {
        type: DataTypes.STRING,
        allowNull: true,
        unique: true, 
      },
      email: {
        type: DataTypes.STRING,
        unique: true,
        allowNull: false,
      },
      password: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      address: {
        type: DataTypes.STRING,
        allowNull: true,
      },
 
      
    },
    {
      sequelize,
      modelName: "User",
      tableName: "Users", // Nama tabel di database
      timestamps: false, // Karena kita menggunakan timestamp dalam format Unix manual, kita set ini ke false
    }
  );

  return User;
};