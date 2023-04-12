const pool = require('../config/database');

module.exports = {
  create: (data, callBack) => {
    pool.query(
      `insert into user_credentials(user_id, password, last_modified_by) values(?,?,?)`,
      [data.user_id, data.password, data.modified_by],
      (error, results, fields) => {
        console.log({ error, results });
        if (error) {
          return callBack(error);
        }
        return callBack(null, {
          email: data.email,
          user_id: results.insertId,
        });
      }
    );
  },

  createUser: (data, callBack) => {
    pool.query(
      `insert into users(user_id, name, role, created_by) values(?,?,?,?)`,
      [data.user_id, data.name, data.role, data.created_by],
      (error, results, fields) => {
        if (error) {
          return callBack(error);
        }
        return callBack(null, {
          id: results.insertId,
          name: data.name,
        });
      }
    );
  },

  login: (data, callBack) => {
    pool.query(
      `Select * from user_credentials where user_id = ${data.user_id}`,
      (error, results, fields) => {
        if (error) {
          return callBack(error);
        }
        return callBack(null, results[0]);
      }
    );
  },
};
