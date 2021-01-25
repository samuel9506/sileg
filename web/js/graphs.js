const GRAPH_SELLS = document.getElementById('grafica-ventas').getContext('2d')
const GRAPH_PURCHASES = document.getElementById('grafica-compras')

let chartSells = new Chart(GRAPH_SELLS,{
    type:'bar',
    data:{
        labels:['January','February','March','April',' May','June','July','August','September','october','November','Decemebr'],
        datasets:[{
            label: 'SILEG sales month by month',
            data:[200000,300000,280000,290000,150000,350000,450000,650000,350000,350000,650000,1250000],
            backgroundColor:[
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)',
                'rgb(105, 209, 117)'
            ]
        }]
    },
    options:{
        scales:{
            yAxes:[{
                ticks:{
                    beginAtZero:true
                }
            }]
        }
    }
})

let chartPurchases =  new Chart(GRAPH_PURCHASES,{
    type:'bar',
    data:{
       labels:['January','February','March','April',' May','June','July','August','September','october','November','Decemebr'],
        datasets:[{
            label: 'SILEG sales month by month',
            data:[200000,300000,280000,290000,150000,350000,450000,650000,350000,350000,650000,1250000],
            backgroundColor:[
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)',
                'rgb(29, 147, 214)'
            ]
        }]
    },
    options:{
        scales:{
            yAxes:[{
                ticks:{
                    beginAtZero:true
                }
            }]
        }
    }
})