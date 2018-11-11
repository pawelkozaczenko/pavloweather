        /*var chartData = {
            labels: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
            datasets: [{
                type: 'bar',
                label: 'Temperature',
                    data: [12, 19, 3, 5, 2, 3, 9],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(0, 255, 255, 0.2)'
                         
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(0, 255, 255, 1)'
                    ],
                    borderWidth: 1
            }, {
                type: 'line',
                label: 'Humidity',
                backgroundColor: 'rgba(255,0,0,0.4)',
                data: [3, 4, 13, 15, 12, 13, 3],
                borderColor: 'white',
                borderWidth: 2
            }, {
                type: 'line',
                label: 'Pressure',
                backgroundColor: 'rgba(0,255,0,0.6)',
                data: [11, 14, 5, 4, 5, 3, 13],
            }]
        };*/

        var chartData = {
            labels: [
            <th:block th:each="item : ${apiData.getDailyUnits()}">
                <th:block th:text="(${apiData.getDailyUnits().indexOf(item)} > 0) ? ',' : ''"></th:block>
                "<th:block th:text="${item.getDate()}"></th:block>"
            </th:block>
            ],
            datasets: [{
                type: 'bar',
                label: 'Humidity',
                    data: [
                     <th:block th:each="item : ${apiData.getDailyUnits()}">
                        <th:block th:text="(${apiData.getDailyUnits().indexOf(item)} > 0) ? ',' : ''"></th:block>
                        <th:block th:text="${item.getHumidity()}"></th:block>
                    </th:block>
                    ],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(0, 255, 255, 0.2)'
                         
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(0, 255, 255, 1)'
                    ],
                    borderWidth: 1
            }, {
                type: 'line',
                label: 'Temperature',
                backgroundColor: 'rgba(255,0,0,0.4)',
                data: [
                 <th:block th:each="item : ${apiData.getDailyUnits()}">
                    <th:block th:text="(${apiData.getDailyUnits().indexOf(item)} > 0) ? ',' : ''"></th:block>
                    <th:block th:text="${item.getTemperature()}"></th:block>
                </th:block>
                ],
                borderColor: 'white',
                borderWidth: 2
            }, {
                type: 'line',
                label: 'Pressure',
                backgroundColor: 'rgba(0,255,0,0.6)',
                data: [
                <th:block th:each="item : ${apiData.getDailyUnits()}">
                    <th:block th:text="(${apiData.getDailyUnits().indexOf(item)} > 0) ? ',' : ''"></th:block>
                    <th:block th:text="${item.getPressure()}"></th:block>
                </th:block>
                ],
            }]
        };

        window.onload = function() {
            var ctx = document.getElementById("myChart").getContext("2d");
            window.myMixedChart = new Chart(ctx, {
                type: 'bar',
                data: chartData,
                options: {
                    responsive: true,
                    title: {
                        display: true,
                        text: 'Week Weather Forecast'
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: true
                    }
                }
            });
        };