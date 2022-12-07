INSERT INTO article(id, title, content) VALUES (1, '1111', 'aaaa');
INSERT INTO article(id, title, content) VALUES (2, '2222', 'bbbb');
INSERT INTO article(id, title, content) VALUES (3, '3333', 'cccc');

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES (4, '당신의 인생 영화는?', '댓글 ㄱ');
INSERT INTO article(id, title, content) VALUES (5, '당신의 소울 푸드는?', '댓글 ㄱㄱ');
INSERT INTO article(id, title, content) VALUES (6, '당신의 취미는?', '댓글 ㄱㄱㄱ');

-- comment 더미 데이터
---- 4번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, '박재언', '스타 워즈');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, '윤현우', '아이언 맨');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, '양준혁', '어바웃 타임');

---- 5번 게시글 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, '윤하연', '치킨');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, '김원섭', '샤브샤브');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, '권지형', '피자');

---- 6번 게시글 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, '이중원', '유튜브');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, '왕새한', '게임');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, '김성수', '운동');