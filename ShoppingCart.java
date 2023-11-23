<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Math Table</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 5px;
            border: 3px solid black;
        }

        th,td {
            border: 1px solid black;
            padding: 1px;
            text-align: center;
            cursor: pointer;
            min-height: 12px;
        }

        .total-container {
            display: flex;
            align-items: center;
            margin-top: 1px;
        }

        .total-label {
            margin-right: 10px;
        }

        .total-button {
            margin-right: 10px; /* Adjusted margin right */
        }

        button {
            padding: 10px;
            margin: 2px;
        }

        #inputNumber1 {
            display: none;
        }

        .scroll-buttons {
            display: flex;
            align-items: center;
            justify-content: flex-end; /* Align buttons to the right */
            flex-direction: column;
        }

        .input-container {
            display: flex;
            align-items: center;
            width: 100%; /* Make the container take up the entire width */
        }

        input[type="number"] {
            width: calc(100% - 10px); /* Adjusted input field width */
            margin-right: 2px; /* Add margin to separate the input field from the buttons */
        }

        .scroll-button {
            cursor: pointer;
            padding: 1px;
            font-size: 2px;
            
            margin-left: 2px; /* Add margin to separate buttons from the input field */
        }
    </style>
</head>
<body>

    <button onclick="addRow()">Add Row</button>
    <table id="mathTable">
        <thead>
            <tr>
                <th >First Number (CA) </th>
                <th >Second Number (CB) </th>
                <th>Operation (CD) </th>
                <th>Answer (CE) </th>
            </tr>
        </thead>
        <tbody>
            
        </tbody>
        <tfoot>
            <tr>
                <td>
                    <div class="total-container">
                        <button onclick="calculateTotal(1)" class="total-button">Total (CA )</button>
                        <span class="total-label">:</span>
                        <span id="total1"></span>
                    </div>
                </td>
                <td>
                    <div class="total-container">
                        <button onclick="calculateTotal(2)" class="total-button">Total (CB)</button>
                        <span class="total-label">:</span>
                        <span id="total2"></span>
                    </div>
                </td>
                <td></td>
                <td></td>
            </tr>
        </tfoot>
    </table>

    <input type="number" id="inputNumber1">


    <script>
        function addRow() {
        var number1 = parseFloat(document.getElementById("inputNumber1").value) || 0;
        var mathTable = document.getElementById("mathTable").getElementsByTagName('tbody')[0];
        var newRow = mathTable.insertRow(mathTable.rows.length);
        var cellNumber1 = newRow.insertCell(0);
        var cellNumber2 = newRow.insertCell(1);
        var cellOperation = newRow.insertCell(2);
        var cellAnswer = newRow.insertCell(3);

        var inputContainer1 = document.createElement("div");
        inputContainer1.className = "input-container";

        var inputNumber1 = document.createElement("input");
        inputNumber1.type = "number";
        inputNumber1.value = 0;
        inputNumber1.className = "input-field";
        inputContainer1.appendChild(inputNumber1);

        var scrollButtons1 = document.createElement("div");
        scrollButtons1.className = "scroll-buttons";

        var scrollUpButton1 = document.createElement("div");
        scrollUpButton1.innerHTML = "&#9650;";
        scrollUpButton1.className = "scroll-button";
        scrollUpButton1.onclick = function () { scrollInput(inputNumber1, 1); };

        var scrollDownButton1 = document.createElement("div");
        scrollDownButton1.innerHTML = "&#9660;";
        scrollDownButton1.className = "scroll-button";
        scrollDownButton1.onclick = function () { scrollInput(inputNumber1, -1); };

        scrollButtons1.appendChild(scrollUpButton1);
        scrollButtons1.appendChild(scrollDownButton1);
        inputContainer1.appendChild(scrollButtons1);

        cellNumber1.appendChild(inputContainer1);

        var inputContainer2 = document.createElement("div");
        inputContainer2.className = "input-container";

        var inputNumber2 = document.createElement("input");
        inputNumber2.type = "number";
        inputNumber2.value = 0;
        inputNumber2.className = "input-field";
        inputContainer2.appendChild(inputNumber2);

        var scrollButtons2 = document.createElement("div");
        scrollButtons2.className = "scroll-buttons";

        var scrollUpButton2 = document.createElement("div");
        scrollUpButton2.innerHTML = "&#9650;";
        scrollUpButton2.className = "scroll-button";
        scrollUpButton2.onclick = function () { scrollInput(inputNumber2, 1); };

        var scrollDownButton2 = document.createElement("div");
        scrollDownButton2.innerHTML = "&#9660;";
        scrollDownButton2.className = "scroll-button";
        scrollDownButton2.onclick = function () { scrollInput(inputNumber2, -1); };

        scrollButtons2.appendChild(scrollUpButton2);
        scrollButtons2.appendChild(scrollDownButton2);
        inputContainer2.appendChild(scrollButtons2);

        cellNumber2.appendChild(inputContainer2);

        var operationContainer = document.createElement("div");
        operationContainer.className = "operation-container";

        var additionButton = document.createElement("button");
        additionButton.innerHTML = "+";
        additionButton.onclick = function () {
            cellAnswer.innerHTML = '';
            cellAnswer.innerHTML = calculateAnswer(parseFloat(inputNumber1.value), parseFloat(inputNumber2.value), 'addition');
        };
        operationContainer.appendChild(additionButton);

        var subtractionButton = document.createElement("button");
        subtractionButton.innerHTML = "-";
        subtractionButton.onclick = function () {
            cellAnswer.innerHTML = '';
            cellAnswer.innerHTML = calculateAnswer(parseFloat(inputNumber1.value), parseFloat(inputNumber2.value), 'subtraction');
        };
        operationContainer.appendChild(subtractionButton);

        var multiplicationButton = document.createElement("button");
        multiplicationButton.innerHTML = "*";
        multiplicationButton.onclick = function () {
            cellAnswer.innerHTML = '';
            cellAnswer.innerHTML = calculateAnswer(parseFloat(inputNumber1.value), parseFloat(inputNumber2.value), 'multiplication');
        };
        operationContainer.appendChild(multiplicationButton);

        var divisionButton = document.createElement("button");
        divisionButton.innerHTML = "/";
        divisionButton.onclick = function () {
            cellAnswer.innerHTML = '';
            cellAnswer.innerHTML = calculateAnswer(parseFloat(inputNumber1.value), parseFloat(inputNumber2.value), 'division');
        };
        operationContainer.appendChild(divisionButton);

        cellOperation.appendChild(operationContainer);

      
        var answerContainer = document.createElement("div");
        answerContainer.className = "answer-container";

        var answerField = document.createElement("div");
        answerField.className = "answer-field";

        answerContainer.appendChild(answerField);
        cellAnswer.appendChild(answerContainer);
    }

    function calculateAnswer(number1, number2, operation) {
        switch (operation) {
            case 'addition':
                return number1 + number2;
            case 'subtraction':
                return number1 - number2;
            case 'multiplication':
                return number1 * number2;
            case 'division':
                return number1 / number2;
            default:
                return 'Invalid Operation';
        }
    }

        function allowEdit(cell) {
          cell.contentEditable = true;
          cell.focus();
        
     
          cell.onblur = function() {
            cell.contentEditable = false;
          };
        }
        
        function updateTotal(rowIndex) {
          var resultCells1 = document.querySelectorAll("#mathTable tbody tr td:nth-child(1)");
          var resultCells2 = document.querySelectorAll("#mathTable tbody tr td:nth-child(2)");
          var totalCell1 = document.getElementById("total1");
          var totalCell2 = document.getElementById("total2");
          var total1 = 0;
          var total2 = 0;
        
          resultCells1.forEach(function(cell) {
            total1 += parseFloat(cell.textContent) || 0;
          });
        
          resultCells2.forEach(function(cell) {
            total2 += parseFloat(cell.textContent) || 0;
          });
        
          totalCell1.textContent = total1;
          totalCell2.textContent = total2;
        
          document.getElementById("totalRow1").style.display = "flex";
          document.getElementById("totalRow2").style.display = "flex";
        }
        
        function calculateTotal(column) {
          var inputFields = document.querySelectorAll("#mathTable tbody tr td:nth-child(" + column + ") input");
          var totalCell = document.getElementById("total" + column);
          var total = 0;
        
          inputFields.forEach(function(inputField) {
            total += parseFloat(inputField.value) || 0;
          });
        
          totalCell.textContent = total;
        
          if (column === 1) {
            document.getElementById("totalRow1").style.display = "flex";
          } else if (column === 2) {
            document.getElementById("totalRow2").style.display = "flex";
          }
        }
        
        function scrollInput(inputField, direction) {
          var currentValue = parseFloat(inputField.value) || 0;
          inputField.value = currentValue + direction;
        }


    </script>
</body>
</html>
