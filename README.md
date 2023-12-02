<div class="relative flex w-[calc(100%-50px)] flex-col gap-1 md:gap-3 lg:w-[calc(100%-115px)]"><div class="flex flex-grow flex-col gap-3"><div class="min-h-[20px] flex flex-col items-start gap-3 overflow-x-auto whitespace-pre-wrap break-words"><div class="markdown prose w-full break-words dark:prose-invert light"><h1>Консольные Шахматы на Java</h1>

[![Downloads](https://img.shields.io/badge/Server-download-red)](https://github.com/porunit/ConsoleChess/releases/download/release/Server.jar)
[![Downloads](https://img.shields.io/badge/Client-download-blue)](https://github.com/porunit/ConsoleChess/releases/download/release/Client.jar)
  
  <h2>Описание</h2><p>Проект представляет себя реализацию классической игры в шахматы с консольным интерфейсом. Система базируется на клиент-серверной архитектуре, где сервер обеспечивает взаимодействие двух клиентов, участвующих в игре. Данные между клиентами и сервером передаются по протоколу TCP/IP.</p><h2>Особенности</h2><ul><li>Консольный интерфейс;</li><li>Клиент-серверная архитектура;</li><li>Поддержка игры между двумя участниками;</li><li>Использование стандартных средств Java без сторонних библиотек.</li></ul><h2>Начало работы</h2><h3>Подготовка</h3><p>Для запуска проекта вам понадобится JDK (Java Development Kit) не ниже 8 версии.</p><p>При необходимости выдайте права доступа файлам:
    <pre><code>chmod 777 Server.jar</code></pre><br>
    <pre><code>chmod 777 Client.jar</code></pre>
  <h3>Запуск сервера</h3><ol><li>Откройте консоль в директории проекта.</li>
    <li>Запустите сервер следующей командой:</li>
  </ol><pre><code class="!whitespace-pre hljs">java -jar Server.jar
</code></pre>
<h3>Запуск клиента</h3><ol><li>Откройте другую консоль в той же директории.</li><li>Запустите клиентское приложение:</li></ol><pre><code class="!whitespace-pre hljs">java -jar Client.jar
</code></pre><p>Запустите еще одного клиента для начала игры.</p>

<h2>Игровой процесс</h2><p>После подключения обоих игроков к серверу, они могут начать игру.</p><ol><li>Введите координаты начальной и конечной точки для вашего хода в формате: <code>E2 E4</code>.</li><li>Подождите, пока другой игрок сделает свой ход.</li></ol><p>Продолжайте в том же духе до завершения партии.</p>
