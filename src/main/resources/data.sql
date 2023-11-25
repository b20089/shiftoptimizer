-- Employees（従業員）テーブルへのダミーデータ挿入
INSERT INTO Employees (Name, SkillLevel, Salary) VALUES
('田中 太郎', 3, 1024.00),
('山本 佳子', 2, 1024.00),
('鈴木 健太', 1, 1024.00),
('伊藤 美紀', 2, 1024.00),
('佐藤 裕子', 3, 1024.00),
('小林 健一', 2, 1024.00),
('高橋 明子', 1, 1024.00),
('渡辺 勇太', 2, 1024.00),
('中村 美和', 3, 1024.00),
('斎藤 勝也', 1, 1024.00);

/*
INSERT INTO ShiftRequests (EmployeeID, ShiftDate, ShiftType, OtherRequestDetails) VALUES
(1, '2023-09-01', 1, '7-9'),
(2, '2023-09-01', 2, '10-12'),
(3, '2023-09-01', 3, '12-14'),
(4, '2023-09-01', 4, '15-17'),
(5, '2023-09-01', 5, '19-21'),
(6, '2023-09-02', 1, '6-9'),
(7, '2023-09-02', 2, '10-13'),
(8, '2023-09-02', 3, '12-15'),
(9, '2023-09-02', 4, '16-18'),
(10, '2023-09-02', 5, '18-21');*/

-- ShiftRequests（シフト要求）テーブルへのダミーデータ挿入
-- 1か月分のデータを挿入
-- INSERT INTO ShiftRequests (EmployeeID, ShiftDate, ShiftType, OtherRequestDetails)
-- SELECT
--     FLOOR(1 + RAND() * 10), -- EmployeeIDをランダムに選択 (1から10の間)
--     --DATE_ADD('2023-09-01', INTERVAL FLOOR(RAND() * 30) DAY), -- ランダムな日付を生成
--     DATEADD(DAY, FLOOR(RAND() * 30), '2023-09-01'),
--     FLOOR(1 + RAND() * 5), -- ShiftTypeをランダムに選択 (1から5の間)
--     CONCAT(FLOOR(RAND() * 24), '時', '~', FLOOR(RAND() * 24), '時') -- 働ける時間帯をランダムに生成
-- FROM(SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
--     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) AS numbers
--     CROSS JOIN
--     (SELECT 1 AS m UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
--     UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) AS months
--     ;

INSERT INTO ShiftRequests (EmployeeID, ShiftDate, ShiftType, StartTime, EndTime)
-- SELECT
--     FLOOR(1 + RAND() * 10),
--     DATEADD(DAY, FLOOR(RAND() * 30), '2023-09-01'),
--     FLOOR(1 + RAND() * 5),
--     PARSE_TIME(TO_CHAR(TIME '00:00' + (FLOOR(RAND() * 24) || ':00:00'), 'HH24:MI:SS')),
--     PARSE_TIME(TO_CHAR(TIME '00:00' + (FLOOR(RAND() * 24) || ':00:00'), 'HH24:MI:SS'))
-- FROM
--     (SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
--      UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) AS numbers
-- CROSS JOIN
--     (SELECT 1 AS m UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
--      UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) AS months;
VALUES
(1, '2023-09-01', 1, '07:00:00', '09:00:00'),
(2, '2023-09-01', 2, '10:00:00', '12:00:00'),
(3, '2023-09-01', 3, '12:00:00', '14:00:00'),
(1, '2023-09-02', 1, '07:30:00', '12:00:00'),
(2, '2023-09-02', 2, '12:00:00', '17:00:00'),
(3, '2023-09-02', 3, '17:00:00', '22:00:00'),
(1, '2023-09-03', 1, '06:00:00', '12:00:00'),
(2, '2023-09-03', 2, '11:00:00', '16:00:00'),
(3, '2023-09-03', 3, '16:00:00', '22:00:00');
/*
-- OptimizedShift（最適化されたシフト）テーブルへのダミーデータ挿入
INSERT INTO OptimizedShift (ShiftDate, ShiftType, EmployeeID) VALUES
('2023-09-01', 1, 1),
('2023-09-01', 2, 2),
('2023-09-01', 3, 3),
('2023-09-01', 4, 4),
('2023-09-01', 5, 5),
('2023-09-02', 1, 6),
('2023-09-02', 2, 7),
('2023-09-02', 3, 8),
('2023-09-02', 4, 9),
('2023-09-02', 5, 10),
-- 以下、1か月分のデータを続けて挿入
-- ...
;
*/

-- OptimizedShift（最適化されたシフト）テーブルへのダミーデータ挿入
-- ShiftRequestsに対応するデータを生成
--だみーなので，今はShiftRequetsをコピーしただけの物を挿入
-- OptimizedShift（最適化されたシフト）テーブルへのダミーデータ挿入
-- ShiftRequestsに対応するデータを生成
INSERT INTO OptimizedShift (ShiftDate, ShiftType, EmployeeID, StartTime, EndTime)
SELECT
    sr.ShiftDate,
    sr.ShiftType,
    sr.EmployeeID,
    sr.StartTime, -- ShiftRequestのStartTimeをOptimizedShiftのStartTimeにコピー
    sr.EndTime -- ShiftRequestのEndTimeをOptimizedShiftのEndTimeにコピー
FROM ShiftRequests sr;
