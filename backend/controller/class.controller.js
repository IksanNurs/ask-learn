const { Class, User, Tutor } = require('../models');
const { validationResult } = require('express-validator');

const createClass = async (req, res) => {
  const { quota, subject, topic, location } = req.body;
  const user_id = req.userId;

  try {
    const newClass = await Class.create({
      quota,
      subject,
      start,
      end,
      topic,
      location,
      user_id,
      khs: req.file ? req.file.filename : null,
    });

    res.status(201).json({
      message: 'Class created successfully',
      data: newClass,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while creating the class' });
  }
};

const getClasses = async (req, res) => {
  try {
    const classes = await Class.findAll({
      include: [
        {
          model: User,
          as: 'user',
          attributes: ['id', 'username']
        },
        {
          model: Tutor,
          as: 'tutors',
          attributes: ['id', 'name', 'date']
        }
      ]
    });

    res.status(200).json({
      message: 'Classes retrieved successfully',
      data: classes,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while fetching classes' });
  }
};

const getClassById = async (req, res) => {
  const classId = req.params.id;

  try {
    const classData = await Class.findByPk(classId, {
      include: [
        {
          model: User,
          as: 'user',
          attributes: ['id', 'username']
        },
        {
          model: Tutor,
          as: 'tutors',
          attributes: ['id', 'name', 'date']
        }
      ]
    });

    if (!classData) {
      return res.status(404).json({ message: 'Class not found' });
    }

    res.status(200).json({
      message: 'Class retrieved successfully',
      data: classData,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while fetching the class' });
  }
};

const updateClass = async (req, res) => {
  const classId = req.params.id;
  const { quota, subject, topic, location } = req.body;

  try {
    const classData = await Class.findByPk(classId);

    if (!classData) {
      return res.status(404).json({ message: 'Class not found' });
    }

    classData.quota = quota || classData.quota;
    classData.subject = subject || classData.subject;
    classData.topic = topic || classData.topic;
    classData.location = location || classData.location;
    classData.start = location || classData.start;
    classData.end = location || classData.end;
    
    if (req.file) {
      classData.khs = req.file.filename;
    }

    await classData.save();

    res.status(200).json({
      message: 'Class updated successfully',
      data: classData,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while updating the class' });
  }
};

const deleteClass = async (req, res) => {
  const classId = req.params.id;

  try {
    const classData = await Class.findByPk(classId);

    if (!classData) {
      return res.status(404).json({ message: 'Class not found' });
    }

    await classData.destroy();

    res.status(200).json({
      message: 'Class deleted successfully',
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while deleting the class' });
  }
};

module.exports = {
  createClass,
  getClasses,
  getClassById,
  updateClass,
  deleteClass,
}; 