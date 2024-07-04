-- First, let's insert some sample users
INSERT INTO user (username, email, password, role) VALUES
                                                       ('john_doe', 'john@example.com', 'hashed_password_123', 'USER'),
                                                       ('jane_smith', 'jane@example.com', 'hashed_password_456', 'ADMIN');

-- Now, let's insert some projects
INSERT INTO project (title, description, technologies, live_demo_link, source_code_link, user_id) VALUES
                                                                                                      ('E-commerce Platform', 'A full-stack e-commerce solution', 'React, Node.js, MongoDB', 'https://demo.ecommerce-platform.com', 'https://github.com/johndoe/ecommerce-platform', 1),
                                                                                                      ('Weather App', 'Real-time weather forecasting application', 'Vue.js, Express, OpenWeatherMap API', 'https://weather.jane-smith.com', 'https://github.com/janesmith/weather-app', 2),
                                                                                                      ('Task Manager', 'Productivity app for managing daily tasks', 'Angular, Spring Boot, PostgreSQL', 'https://taskmanager.john-doe.com', 'https://github.com/johndoe/task-manager', 1),
                                                                                                      ('Portfolio Website', 'Personal portfolio showcasing projects', 'HTML, CSS, JavaScript', 'https://jane-smith-portfolio.com', 'https://github.com/janesmith/portfolio', 2),
                                                                                                      ('Blog Engine', 'Custom blog engine with admin panel', 'Django, React, MySQL', NULL, 'https://github.com/johndoe/blog-engine', 1);