// シフトを保存する処理
function saveShift() {
  var employeeName = document.getElementById('employeeName').value;
  var employeeID = document.getElementById('employeeID').value;
  var shiftDate = document.getElementById('shiftDate').value;
  var startTime = document.getElementById('startTime').value;
  var endTime = document.getElementById('endTime').value;

  // 入力が正しいか確認（必要に応じて追加のバリデーションを行う）

  // クライアントからサーバーにシフトを追加するイベントを送信
  var eventData = {
    employeeName: employeeName,
    employeeID: employeeID,
    shiftDate: shiftDate,
    startTime: startTime,
    endTime: endTime
  };

  fetch("/shifts/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(eventData)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Failed to add shift");
      }
      console.log("Shift added successfully");
    })
    .catch(error => {
      console.error("Error adding shift:", error);
      // エラーが発生した場合に適切なエラーハンドリングを行う
    });
}
