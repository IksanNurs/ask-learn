const express = require('express');
const router = express.Router();
const controller = require('../controller/tutor.controller');
const authentication = require('../middleware/authentication');

router.post('/tutors', authentication, controller.createTutor);
router.get('/tutors', authentication, controller.getTutors);
router.get('/tutors/:id', authentication, controller.getTutorById);
router.put('/tutors/:id', authentication, controller.updateTutor);
router.delete('/tutors/:id', authentication, controller.deleteTutor);

module.exports = router; 