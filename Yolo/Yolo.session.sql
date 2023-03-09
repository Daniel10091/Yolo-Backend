-- use yolo;


SELECT p.name, u.username, e.email
  FROM person p 
  INNER JOIN user u ON u.id = p.id
  LEFT OUTER JOIN emails e ON e.id = p.id;