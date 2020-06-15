# language: pl
Funkcja: Informacja dla podróżnych o czasie przybycia do stacji docelowej
	W celu bardziej efektywnego planowania podróży
	Jako podróżny
	Chcę wiedzieć, o której godzinie dotrę do stacji docelowej

Szablon scenariusza: Szacowanie czasu przyjazdu
    Zakładając chcę się dostać z <skąd> do <dokąd>
    I następny pociąg odjeżdża o <czas_odjazdu> na linii <linia>
    Gdy zapytam o godzinę przyjazdu
    Wtedy powinienem uzyskać następujący szacowany czas przyjazdu: <czas_przyjazdu>

    Przykłady:
    | skąd          |      dokąd    |       linia     | czas_odjazdu |   czas_przyjazdu   |
    | Parramatta    | Town Hall     | 8:02            | Western      | 8:29               |