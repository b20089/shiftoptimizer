-- Employees（従業員）テーブルの作成
CREATE TABLE Employees (
    EmployeeID BIGINT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    SkillLevel INT NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL
);

-- ShiftRequests（シフト要求）テーブルの作成
CREATE TABLE ShiftRequests (
    RequestID BIGINT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID BIGINT,
    ShiftDate DATE NOT NULL,
    ShiftType INT NOT NULL,
    StartTime TIME,
    EndTime TIME,
    OtherRequestDetails VARCHAR(255),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- OptimizedShift（最適化されたシフト）テーブルの作成
CREATE TABLE OptimizedShift (
    ShiftID BIGINT PRIMARY KEY AUTO_INCREMENT,
    ShiftDate DATE NOT NULL,
    ShiftType INT NOT NULL,
    EmployeeID BIGINT,
    --WorkingHours VARCHAR(255),
    StartTime TIME,
    EndTime TIME,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);
