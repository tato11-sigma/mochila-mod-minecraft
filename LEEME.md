# Mod "Mochila" para Minecraft Java 26.3 (Fabric)

## Que hace
- Item **Mochila** (receta: 7 cuero, 1 hilo, 1 cofre; o `/give @s backpackmod:backpack`).
- **Slot propio** en el inventario (al lado del slot de la mano secundaria). Se equipa arrastrando
  la mochila al slot, o con clic derecho con la mochila en la mano.
- **Clic derecho sobre el slot** de la mochila (con el cursor vacio) = abre 27 casillas de almacenamiento.
- Se ve **en 3D en la espalda** (tercera persona), tambien para los demas jugadores.
- La mochila equipada se conserva al morir.

## Como compilarlo sin instalar nada (GitHub)
1. Crea una cuenta en github.com y un repositorio nuevo (privado esta bien).
2. Subi todo el contenido de esta carpeta (incluida la carpeta oculta `.github`).
3. Entra en la pestana **Actions**, abri el job **build** y esperá a que termine.
4. Abajo, en **Artifacts**, descarga `backpackmod-jar`: adentro esta `backpackmod-1.0.0.jar`.
5. Copiala a `.minecraft/mods` junto con **Fabric API 0.161.0+26.3** y Fabric Loader 0.19.5+.

## Para jugar
Hace falta Java 25 y el launcher con Fabric instalado para 26.3.
