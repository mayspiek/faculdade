<script>
	import Artista from "./Artista.svelte";
import Movie from "./Movie.svelte";
	import Nav from "./Nav.svelte";
</script>

<Nav/>
<div class="conteudo">
	<Movie/>
	<Artista/>
</div>

<style>
	.conteudo{display: flex;
		flex-direction: row;
		justify-content: space-around;}
</style>
