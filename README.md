**Заглушка на Maven SpringBoot:**
1.Адрес: http://127.0.0.1:8080/api/datetime  
2.Http методы POST/PUT  
3.Тело в формате JSON:  
  {  
    "time": ""yyyy-MM-dd'T'HH:mm:ss""  
  }  
Пример:  
{  
    "time": "2203-03-15T14:30:00"  
}  
4.Реализованы:  
  5% ошибка  
  проверка формата времени  
  Случайная задержка 200-400мс  
