'use strict';
const { Order, Product, User } = require('../models');  // Assuming Order, Product, and User models
const { validationResult } = require('express-validator');  // Validation imports
const axios = require('axios');





// Create a new order
const createOrder = async (req, res) => {
    const { class_id, } = req.body;  // Only class_id is now required in the request body
    const userId = req.userId;  // Get the user ID from the JWT token (set by the middleware)
  
    // Validate request body
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(400).json({ errors: errors.array() });
    }
  
    try {
      // Fetch the user to associate with the order
      const user = await User.findByPk(userId);
      if (!user) {
        return res.status(404).json({ message: `User with ID ${userId} not found` });
      }
  
      // Fetch the product associated with the given class_id
      const product = await Product.findByPk(class_id);
      if (!product) {
        return res.status(404).json({ message: `Product with ID ${class_id} not found` });
      }
  
      // Fetch the price for the product
      const price = product.price;
  
      // Create a new order entry in the database
      const newOrder = await Order.create({
        user_id: userId,
        class_id, 
      });
  
  
      // Return success response with the payment URL and the created order
      res.status(201).json({
        message: 'Order created successfully',
        data: {
          order: newOrder,
        },
      });
    } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'An error occurred while creating the order' });
    }
  };
  
// Get all orders
const getOrders = async (req, res) => {
  try {
    const userId = req.userId;
    const { status } = req.query;

    // Basic attributes to retrieve from the orders table
    const orderAttributes = [
      'id','user_id', 'class_id', 'status',
    ];

    // Start the query
    const queryOptions = {
      where: {
        user_id: userId
      },
      attributes: orderAttributes,
      include: [
        {
          model: User,
          as: 'user',
          attributes: ['id', 'username'],
        },
        {
          model: Product,
          as: 'product',
          attributes: ['id', 'name', 'price', 'image'],
        },
      ],
    };

    // Add status condition if status query parameter exists
    if (status) {
      queryOptions.where.status = status.replace(/['"]+/g, '');
    }

    // Fetch orders based on the query options
    const orders = await Order.findAndCountAll(queryOptions);

    // Return the response
    res.status(200).json({
      message: 'Orders retrieved successfully',
      data: orders.rows,
    });

  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while fetching the orders' });
  }
};

  

// Get an order by ID
const getOrderById = async (req, res) => {
    const orderId = req.params.id;
  
    try {
      const order = await Order.findOne({
        where: { id: orderId },
        attributes: [
          'user_id',
          'class_id',
          'status',
        ],
        include: [
          {
            model: User,
            as: 'user',
            attributes: ['id', 'username'],
          },
          {
            model: Product,
            as: 'product',  // Sesuaikan alias dengan asosiasi
            attributes: ['id', 'name', 'price', 'image'],
          },
       
        ],
      });
  
      if (!order) {
        return res.status(404).json({ message: `Order with ID ${orderId} not found` });
      }
  
      res.status(200).json({
        message: 'Order retrieved successfully',
        data: order,
      });
    } catch (error) {
      console.error(error);
      res.status(500).json({ message: 'An error occurred while fetching the order' });
    }
  };
  

// Update an order by ID
const updateOrder = async (req, res) => {
  const orderId = req.params.id;
  const { class_id, status, } = req.body;

  try {
    const order = await Order.findOne({ where: { id: orderId } });

    if (!order) {
      return res.status(404).json({ message: `Order with ID ${orderId} not found` });
    }

    const updates = {};
    
    if (class_id !== undefined) {
      const product = await Product.findByPk(class_id);
      if (!product) {
        return res.status(404).json({ message: `Product with ID ${class_id} not found` });
      }
      updates.class_id = class_id;
    }

    if (status !== undefined) updates.status = status;
    
    // Add file handling
    if (req.file) {
      updates.file = req.file.filename;
    }

    await order.update(updates);

    res.status(200).json({
      message: 'Order updated successfully',
      data: order
    });
    
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while updating the order' });
  }
};


  

module.exports = {
  createOrder,
  getOrders,
  getOrderById,
  updateOrder,
};
