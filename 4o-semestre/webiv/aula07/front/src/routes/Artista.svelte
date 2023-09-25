<script>
    let promise = getArtista();
        async function getArtista(nome) {
            // faz um request GET para endpoint /filmes
            const res = await fetch(`http://localhost:8000/artista/${nome}`);
            const text = await res.json();
            if (res.ok) {
                return text;
            } else {
                throw new Error(text);
            }
        }
        function handleClick(nome) {
            promise = getArtista(nome);
        }

        function getNome(){
            let nome_artista = document.getElementById("artista_nome").value;
        }
        </script>

<div>
    <input type="text" id="artista_nome"><button onClick=handleClick(nome_artista)> Get Artistas </button>

    {#await promise}
        <p>...waiting</p>
    {:then artistas}
        <h1>Lista de Artistas</h1>
        {#each artistas as artista}
            <p>{artista.name}</p>
        {/each}
    {:catch error}
        <p style="color: red">{error.message}</p>
    {/await}
</div>
