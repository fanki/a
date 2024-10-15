<script>
	import { Button } from '$lib/components/ui/button';
	import { toast } from 'svelte-sonner';
</script>

<h1>Welcome to AnyGUI</h1>
<Button on:click={() => toast('Hallo Flavio dä isch für di')}>Hello</Button>
