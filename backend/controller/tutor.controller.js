const { Tutor, Class } = require('../models');
const { validationResult } = require('express-validator');

const createTutor = async (req, res) => {
  const { name, class_id } = req.body;

  try {
    const classExists = await Class.findByPk(class_id);
    if (!classExists) {
      return res.status(404).json({ message: 'Class not found' });
    }

    const newTutor = await Tutor.create({
      name,
      class_id,
    });

    res.status(201).json({
      message: 'Tutor created successfully',
      data: newTutor,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while creating the tutor' });
  }
};

const getTutors = async (req, res) => {
  try {
    const tutors = await Tutor.findAll({
      include: [{
        model: Class,
        as: 'class',
        attributes: ['id', 'subject', 'topic']
      }]
    });

    res.status(200).json({
      message: 'Tutors retrieved successfully',
      data: tutors,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while fetching tutors' });
  }
};

const getTutorById = async (req, res) => {
  const tutorId = req.params.id;

  try {
    const tutor = await Tutor.findByPk(tutorId, {
      include: [{
        model: Class,
        as: 'class',
        attributes: ['id', 'subject', 'topic']
      }]
    });

    if (!tutor) {
      return res.status(404).json({ message: 'Tutor not found' });
    }

    res.status(200).json({
      message: 'Tutor retrieved successfully',
      data: tutor,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while fetching the tutor' });
  }
};

const updateTutor = async (req, res) => {
  const tutorId = req.params.id;
  const { name, class_id } = req.body;

  try {
    const tutor = await Tutor.findByPk(tutorId);

    if (!tutor) {
      return res.status(404).json({ message: 'Tutor not found' });
    }

    if (class_id) {
      const classExists = await Class.findByPk(class_id);
      if (!classExists) {
        return res.status(404).json({ message: 'Class not found' });
      }
      tutor.class_id = class_id;
    }

    tutor.name = name || tutor.name;
    await tutor.save();

    res.status(200).json({
      message: 'Tutor updated successfully',
      data: tutor,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while updating the tutor' });
  }
};

const deleteTutor = async (req, res) => {
  const tutorId = req.params.id;

  try {
    const tutor = await Tutor.findByPk(tutorId);

    if (!tutor) {
      return res.status(404).json({ message: 'Tutor not found' });
    }

    await tutor.destroy();

    res.status(200).json({
      message: 'Tutor deleted successfully',
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'An error occurred while deleting the tutor' });
  }
};

module.exports = {
  createTutor,
  getTutors,
  getTutorById,
  updateTutor,
  deleteTutor,
}; 