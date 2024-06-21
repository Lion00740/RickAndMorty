## Мобильное приложение по Рику и Морти

Версия проекта: 1.2
UPD: Исправлены некоторые ошибки, а также добавлен поиск и немного изменен дизайн поиска (убран значок лупы). В пункте 5 изображен новый поиск

Приложение собрано и написано на [Kotlin](https://kotlinlang.org/) и с использованием [Retrofit](https://square.github.io/retrofit/), [Room](https://developer.android.com/jetpack/androidx/releases/room) и с использованием [чистой архитектуры](https://developer.android.com/topic/architecture) и архитектурного паттерна MVVM.

Приложение получает список персонажей с открытого API по Рику и Морти, но список также можно просмотреть и без доступа в интернет. Также можно добавить понравившихся персонажей к себе в заметки и отсортировать список по заметкам.

Ссылка на API: https://rickandmortyapi.com/

Работа приложения:
1. Запуск приложения
	1. С интернетом

	   ![mainActivity](https://github.com/Lion00740/RickAndMorty/blob/master/images/mainActivity.png)
	2. Без интернета

	   При запуске приложения будет выводиться сообщение, о том что возникли какие-то трудности, но сам список просмотреть можно, как и добавить персонажей в закладки

	   ![mainActivityError](https://github.com/Lion00740/RickAndMorty/blob/master/images/mainActivityError.png)
2. Просмотр конкретного элемента

   У просматриваемого персонажа появляются новые данные (**Происхождение и локация**)

   ![aboutActivity](https://github.com/Lion00740/RickAndMorty/blob/master/images/aboutCharacter.png)
3. Добавление понравившегося персонажа в закладки
	1. Добавление на главном экране

	   ![setBookmarkMainActivity](https://github.com/Lion00740/RickAndMorty/blob/master/images/setBookmarkOnMainActivity.png)
	2. Добавление на экране персонажа

	   ![setBookmarkAboutActivity](https://github.com/Lion00740/RickAndMorty/blob/master/images/setBookmarkOnAboutActivity.png)
4. Отображение списка персонажей, которые были добавлены в закладки

   По нажатию в правом верхнем углу на иконку закладок, появится список отмеченных персонажей, также в левом верхнем углу появляется стрелочка, которая позволит вернуться обратно к полному списку

   ![filterBookmarks](https://github.com/Lion00740/RickAndMorty/blob/master/images/filterBookmarks.png)
5. Сортировка

   Вверху экрана находится editText и если заполнить его, то будет производиться поиск по именам. Причем поиск можно производить отдельно как над основным списком, так и над закладками

   ![search](https://github.com/Lion00740/RickAndMorty/blob/master/images/search.png)
