Test przeprowadzane są na dwóch folderach, każdy z nich zawiera 2 pliki tekstowe i 1 obraz.

Początkowe ustawienie JVM (-Xmx1024m):
-pliki tekstowe są zapamiętywane przed załadowania pliku .png po raz pierwszy
-po załadowaniu pliku .png i wczytaniu plików tekstowych (po raz pierwszy lub ponownie) referencje zostają zachowane
-po powrocie do folderu początkowego i ponownym wejściu do folderu personalnego referencje zostają zachowane
-po załadowaniu wszystkich plików, dopiero po wczytaniu jednego z plików tekstowych pierwszego folderu stracił referencje

Zmniejszenie pamięci maksymalnej w JVM (-Xmx512m):
-po wczytaniu plików z obydwu folderów, pliki z pierwszego katalogu tracą referencje (Grarbage collector częściej usuwa referencje)

Zwiększenie maksymalnej pamięci w JVM (-Xmx4096m):
-po wczytaniu plików z obydwu folderów wszystkie referencje zostają zachowane

Ustawienie początkowej pamięci na 512 (-Xms512m -Xmx1024m):
-po wczytaniu plików z obydwu folderów, pliki niektóre pliki (obydwa tekstowe) z pierwszego katalogu tracą referencje

Ustawienie algorytmu (-XX:+ShrinkHeapInSteps -Xmx1024m):
-po wczytaniu plików z obydwu folderów, pliki niektóre pliki (obydwa tekstowe) z pierwszego katalogu tracą referencje

Ustawienie algorytmu (-XX:-ShrinkHeapInSteps -Xmx1024m):
-po wczytaniu plików z obydwu folderów, pliki niektóre pliki (obydwa tekstowe) z pierwszego katalogu tracą referencje

Selekcja algorytmu (-XX:+UseSerialGC -Xmx1024m):
-po wczytaniu plików z obydwu folderów, pliki niektóre pliki (obydwa tekstowe) z pierwszego katalogu tracą referencje

Selekcja algorytmu (-XX:+UseParNewGC -Xmx1024m):
-bez zmian

Selekcja algorytmu (-XX:+UseG1GC -Xmx1024m):
-bez zmian

Selekcja algorytmu (-XX:+UseParallelGC -Xmx1024m):
-bez zmian