const express = require('express');
const bodyParse = require('body-parser');
const cors = require('cors');
const userRoutes = require('./routes/auth/auth');
const app = express();

app.use(bodyParse.json());
app.use(cors());

app.use('/auth', cors(), userRoutes);

app.listen(8000, () => {
  console.log('listening');
});
