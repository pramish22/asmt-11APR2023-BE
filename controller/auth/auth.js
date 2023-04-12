const bcrypt = require('bcrypt');
const { sign } = require('jsonwebtoken');
const jwtDecode = require('jwt-decode');
const { create, login, createUser } = require('../../services/db_services');

function genPassword() {
  const chars =
    '0123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*()ABCDEFGHIJKLMNOPQRSTUVWXYZ';
  const passwordLength = 12;
  let password = '';
  for (let i = 0; i <= passwordLength; i++) {
    const randomNumber = Math.floor(Math.random() * chars.length);
    password += chars.substring(randomNumber, randomNumber + 1);
  }
  return password;
}

exports.createUser = async (req, res, next) => {
  const body = req.body;
  const password = genPassword();
  const hashedPassword = await bcrypt.hash(password, 10);
  body.password = hashedPassword;
  const userData = {
    user_id: body.user_id,
    password: hashedPassword,
    last_modified_by: '',
  };

  const userDetails = {
    user_id: body.user_id,
    name: body.name,
    role: body.name,
    created_by: body.created_by,
  };
  create(userData, (err, results) => {
    if (err) {
      res.status(400).json({ returnMessage: 'bad credentials' });
    }
    res.status(200).json({ 'your password': password });
  });

  createUser(userDetails, (err, results) => {
    if (err) {
      res.status(400).json({ returnMessage: 'bad credentials' });
    }
    res.status(200).json('Success');
  });
};

exports.loginUser = async (req, res, next) => {
  const body = req.body;
  const user_id = body.user_id;
  const password = body.password;
  const loginData = {
    user_id: user_id,
    password: password,
  };
  login(loginData, async (err, results) => {
    if (err) {
      res.status(400).json({ returnMessage: 'bad credentials' });
    }

    const result = await bcrypt.compare(password, results.password);
    if (result) {
      results.password = undefined;
      const jsontoken = sign(
        { result: results, role: results.role },
        process.env.JSON_SECRET_KEY,
        {
          expiresIn: '1h',
        }
      );
      return res.status(200).json({
        token: jsontoken,
        userDetails: {
          user_id: results.user_id,
          name: results.name,
        },
      });
    } else {
      return res.status(400).json('Invalid email or password');
    }
  });
};
